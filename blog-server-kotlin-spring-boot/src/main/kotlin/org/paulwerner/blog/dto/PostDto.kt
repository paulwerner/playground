package org.paulwerner.blog.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class PostDto(

    @field: NotNull(message = "{post.title.required}")
    @field: NotEmpty(message = "{post.title.required}")
    var title: String,

    @field: NotNull(message = "{post.content.required}")
    @field: NotEmpty(message = "{post.content.required}")
    var content: String,

    @field: NotNull(message = "{post.category.required}")
    @field: NotEmpty(message = "{post.category.required}")
    var category: String

)