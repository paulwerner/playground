package org.paulwerner.blog.controller

import org.paulwerner.blog.dto.CategoryDto
import org.paulwerner.blog.dto.PostDto
import org.paulwerner.blog.service.BlogService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/blog")
class BlogController(private var blogService: BlogService) {


    @PostMapping("/categories")
    @ResponseStatus(CREATED)
    fun createCategory(@Valid @RequestBody categoryDto: CategoryDto) {
        blogService.createCategory(categoryDto)
    }

    @PostMapping("/posts")
    @ResponseStatus(CREATED)
    fun createPost(@Valid @RequestBody postDto: PostDto) {
        blogService.createPost(postDto)
    }

}