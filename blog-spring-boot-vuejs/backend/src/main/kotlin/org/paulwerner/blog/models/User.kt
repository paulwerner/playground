package org.paulwerner.blog.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User(

        @Id
        val _id: ObjectId? = null,
        val username: String,
        val password: String,
        val roles: List<Role>

)
