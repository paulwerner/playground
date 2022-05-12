package org.paulwerner.blog.security

import org.paulwerner.blog.repositories.UsersRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class MongoUserDetailsService(
        private val usersRepository: UsersRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = usersRepository.findByUsername(username)
                ?: throw UsernameNotFoundException("User=[$username] not found")
        val authorities = arrayListOf<GrantedAuthority>(SimpleGrantedAuthority("ADMIN"))

        return User(user.username, user.password, authorities)
    }

}
