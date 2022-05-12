package org.paulwerner.blog.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "posts")
data class Post(@Id var id: String? = null,
                var title: String,
                var content: String,
                @DBRef var category: Category)