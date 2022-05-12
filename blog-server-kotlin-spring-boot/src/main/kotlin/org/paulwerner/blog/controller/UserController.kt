package org.paulwerner.blog.controller

import org.paulwerner.blog.dto.UserDto
import org.paulwerner.blog.service.UserService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/users")
class UserController(private var userService: UserService) {


    @PostMapping
    @ResponseStatus(CREATED)
    fun createUser(@Valid @RequestBody userDto: UserDto) {
        userService.createUser(userDto)
    }

}