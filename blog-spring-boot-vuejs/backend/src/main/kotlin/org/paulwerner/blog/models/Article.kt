package org.paulwerner.blog.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.format.annotation.DateTimeFormat
import java.util.Date

@Document("articles")
data class Article(

        @Id
        var _id: ObjectId? = null,
        @Indexed(unique = true)
        var slug: String,
        var title: String,
        var author: String,
        var intro: String,
        var content: String,
        @CreatedDate
        var createdDate: Date? = null,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        var publishedDate: Date? = null,
        @DBRef
        var categories: List<Category>

)
