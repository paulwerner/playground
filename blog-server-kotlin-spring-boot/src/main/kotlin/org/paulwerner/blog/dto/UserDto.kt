package org.paulwerner.blog.dto

import org.hibernate.validator.constraints.Length
import org.paulwerner.blog.security.validation.PasswordMatches
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@PasswordMatches
data class UserDto(

    @field: NotNull(message = "{user.firstname.required}")
    @field: Length(min = 2, max = 30, message = "{user.firstname.length}")
    var firstname: String,

    @field: NotNull(message = "{user.lastname.required}")
    @field: Length(min = 2, max = 30, message = "{user.lastname.length}")
    var lastname: String,

    @field: NotEmpty(message = "{user.email.required}")
    @field: Email(message = "{user.email.valid}")
    var email: String,

    @field: NotNull(message = "{user.password.required}")
    @field: Length(min = 8, message = "{user.password.length}")
    var password: String,
    var matchingPassword: String,

    @field: NotEmpty
    var authorities: List<String>

)