package org.paulwerner.blog.exceptions

import org.springframework.http.HttpStatus

class AuthenticationFailedException(val error: String, val status: HttpStatus) : RuntimeException(error)
