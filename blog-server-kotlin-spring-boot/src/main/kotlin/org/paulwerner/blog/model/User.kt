package org.paulwerner.blog.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User(@Id var id: String? = null,
                var firstname: String,
                var lastname: String,
                var email: String,
                var password: String,
                var authorities: Array<String>)