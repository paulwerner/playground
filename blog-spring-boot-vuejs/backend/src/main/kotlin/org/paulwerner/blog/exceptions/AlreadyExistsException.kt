package org.paulwerner.blog.exceptions

class AlreadyExistsException(val error: String) : RuntimeException(error)
