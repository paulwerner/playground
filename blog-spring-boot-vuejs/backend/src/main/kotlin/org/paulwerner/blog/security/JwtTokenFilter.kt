package org.paulwerner.blog.security

import org.paulwerner.blog.exceptions.AuthenticationFailedException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenFilter(
        private val jwtTokenProvider: JwtTokenProvider
) : GenericFilterBean() {

    override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
        val token: String? = jwtTokenProvider.resolveToken(req as HttpServletRequest)

        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                val auth: Authentication = jwtTokenProvider.getAuthentication(token)
                SecurityContextHolder.getContext().authentication = auth
            }

        } catch (e: AuthenticationFailedException) {
            val response = res as HttpServletResponse
            response.sendError(e.status.value(), e.message)
            return
        }

        chain.doFilter(req, res)
    }

}
