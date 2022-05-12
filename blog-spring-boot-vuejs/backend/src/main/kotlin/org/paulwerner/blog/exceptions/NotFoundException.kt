package org.paulwerner.blog.exceptions

class NotFoundException(val error: String) : RuntimeException(error)
