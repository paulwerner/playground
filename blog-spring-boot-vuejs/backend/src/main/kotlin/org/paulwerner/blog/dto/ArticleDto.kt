package org.paulwerner.blog.dto

import java.util.Date

data class ArticleDto(

        val slug: String? = null,
        val title: String,
        val author: String,
        val intro: String,
        val content: String,
        val createdDate: Date? = null,
        val publishedDate: Date? = null,
        val categories: List<CategoryDto>

)

