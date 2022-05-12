package org.paulwerner.blog.security

import org.paulwerner.blog.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class SecUserDetailsService(private var userRepository: UserRepository) : UserDetailsService {


    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByEmail(username)
            ?: throw UsernameNotFoundException("Not user found with email=[$username]")

        return SecUserDetails(user)
    }

}