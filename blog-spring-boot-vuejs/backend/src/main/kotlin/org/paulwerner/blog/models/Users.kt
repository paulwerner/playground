package org.paulwerner.blog.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("users")
data class Users(
    @Id
    val _id: ObjectId,
    val username: String,
    val password: String
)
