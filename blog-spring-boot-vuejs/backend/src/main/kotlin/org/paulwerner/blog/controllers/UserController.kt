package org.paulwerner.blog.controllers

import org.paulwerner.blog.dto.UserDto
import org.paulwerner.blog.services.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {


    @PostMapping("/login")
    fun login(userDto: UserDto): String =
            userService.login(userDto)

    @PostMapping("/signup")
    fun signup(userDto: UserDto): String =
            userService.signUp(userDto)

}
