package org.paulwerner.blog.dto

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull

data class CategoryDto(

    @field : NotNull(message = "{category.title.required}")
    @field: Length(min = 2, max = 30, message = "{category.title.length}")
    var title: String

)