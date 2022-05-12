package org.paulwerner.blog.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.paulwerner.blog.exceptions.AuthenticationFailedException
import org.paulwerner.blog.models.Role
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest


@Component
class JwtTokenProvider(private var userDetailsService: MongoUserDetailsService) {

    @Value("\${security.jwt.token.secret-key:secret-key}")
    private var secretKey: String = "secret-key"

    @Value("\${security.jwt.token.expire-length:3600000}")
    private var validityInMilliseconds: Long = 3600000 // 1h


    fun createToken(username: String, roles: List<Role>): String {
        val now = Date()
        val validity = Date(now.time + validityInMilliseconds)
        val claims = Jwts.claims().setSubject(username)

        claims["auth"] = roles
                .map { role -> SimpleGrantedAuthority(role.authority) }
                .filter(Objects::nonNull)
                .toList()

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact()
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken: String? = request.getHeader("Authorization")
        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7, bearerToken.length)
        } else {
            null
        }
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            return true

        } catch (e: Exception) {
            throw AuthenticationFailedException("Expired or invalid JWT token", HttpStatus.UNAUTHORIZED)
        }
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails = userDetailsService.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getUsername(token: String): String =
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject

}
