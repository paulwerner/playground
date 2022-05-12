package org.paulwerner.blog.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("categories")
data class Category(

        @Id
        var _id: ObjectId? = null,
        val name: String

)
