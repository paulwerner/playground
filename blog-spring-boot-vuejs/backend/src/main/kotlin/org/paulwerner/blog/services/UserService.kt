package org.paulwerner.blog.services

import org.paulwerner.blog.dto.UserDto
import org.paulwerner.blog.exceptions.AuthenticationFailedException
import org.paulwerner.blog.exceptions.NotFoundException
import org.paulwerner.blog.models.User
import org.paulwerner.blog.repositories.UsersRepository
import org.paulwerner.blog.security.JwtTokenProvider
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
        private val usersRepository: UsersRepository,
        private val passwordEncoder: PasswordEncoder,
        private val jwtTokenProvider: JwtTokenProvider,
        private val authenticationManager: AuthenticationManager
) {

    fun login(userDto: UserDto): String {
        val foundUser = usersRepository.findByUsername(userDto.username)
                ?: throw NotFoundException("Username with name=[${userDto.username}] could not be found")

        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(userDto.username, userDto.password))
            return jwtTokenProvider.createToken(userDto.username, foundUser.roles)

        } catch (e: AuthenticationException) {
            throw AuthenticationFailedException(
                    "invalid username/password",
                    HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    fun signUp(userDto: UserDto): String {
        val foundUser = usersRepository.findByUsername(userDto.username)
        if (foundUser == null) {
            val user = User(
                    username = userDto.username,
                    password = passwordEncoder.encode(userDto.password),
                    roles = userDto.roles ?: listOf()
            )
            usersRepository.save(user)
            return jwtTokenProvider.createToken(user.username, user.roles)

        } else {
            throw AuthenticationFailedException(
                    "Username=[${userDto.username} already in use]",
                    HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    fun findByUsername(username: String): User =
            usersRepository.findByUsername(username)
                    ?: throw NotFoundException("Username with name=[$username] could not be found")

}
