package org.paulwerner.blog.controllers

import mu.KLogging
import org.paulwerner.blog.exceptions.AlreadyExistsException
import org.paulwerner.blog.exceptions.AuthenticationFailedException
import org.paulwerner.blog.exceptions.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ExceptionHandlers {

    companion object : KLogging()

    @ExceptionHandler(Exception::class)
    fun handleError(e: Exception, request: HttpServletRequest): ResponseEntity<String> {
        logger.error { e.message }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(toBody("Unexpected Error occurred"))
    }

    @ExceptionHandler(UsernameNotFoundException::class, NotFoundException::class)
    fun handleNotFound(e: RuntimeException, request: HttpServletRequest): ResponseEntity<String> {
        logger.error { e.message }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(toBody(e.message))
    }

    @ExceptionHandler(AlreadyExistsException::class)
    fun handleConflict(e: AlreadyExistsException, request: HttpServletRequest): ResponseEntity<String> {
        logger.error { e.message }
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(toBody(e.message))
    }

    @ExceptionHandler(AuthenticationFailedException::class)
    fun handleAuthenticationFailed(e: AuthenticationFailedException, request: HttpServletRequest): ResponseEntity<String> {
        logger.error { e.message }
        return ResponseEntity
                .status(e.status)
                .body(toBody(e.message))
    }

    private fun toBody(message: String?) =
            "{\"error\": \"$message\"}"

}
