package org.paulwerner.blog.security.validation

import org.paulwerner.blog.dto.UserDto
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext


class PasswordMatchesValidator : ConstraintValidator<PasswordMatches, Any> {

    override fun isValid(value: Any, context: ConstraintValidatorContext): Boolean {
        val user = value as UserDto
        return user.password == user.matchingPassword
    }

}
