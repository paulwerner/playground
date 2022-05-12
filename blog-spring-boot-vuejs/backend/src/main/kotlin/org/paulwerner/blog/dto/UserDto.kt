package org.paulwerner.blog.dto

import org.bson.types.ObjectId
import org.paulwerner.blog.models.Role


data class UserDto(

        var id: ObjectId? = null,
        val username: String,
        val password: String,
        val roles: List<Role>? = null

)
