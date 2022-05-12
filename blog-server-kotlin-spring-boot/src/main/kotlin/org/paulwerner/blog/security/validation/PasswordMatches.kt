package org.paulwerner.blog.security.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.TYPE
import kotlin.reflect.KClass


@Target(TYPE, CLASS)
@Retention(RUNTIME)
@Constraint(validatedBy = [PasswordMatchesValidator::class])
@MustBeDocumented
annotation class PasswordMatches(val message: String = "Passwords don't match",
                                 val groups: Array<KClass<*>> = [],
                                 val payload: Array<KClass<out Payload>> = [])