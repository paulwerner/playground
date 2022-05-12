package org.paulwerner.blog.service

import org.paulwerner.blog.dto.UserDto
import org.paulwerner.blog.exceptions.EmailAlreadyInUseException
import org.paulwerner.blog.model.User
import org.paulwerner.blog.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository,
                  private val passwordEncoder: PasswordEncoder) {

    fun createUser(userDto: UserDto) {
        if (emailAlreadyUsed(userDto.email)) {
            throw EmailAlreadyInUseException("User with email=[${userDto.email}] already exists")
        }

        val user = User(
            firstname = userDto.firstname,
            lastname = userDto.lastname,
            email = userDto.email,
            password = passwordEncoder.encode(userDto.password),
            authorities = userDto.authorities.toTypedArray())

        userRepository.save(user)
    }

    private fun emailAlreadyUsed(email: String) = userRepository.findByEmail(email) != null

}