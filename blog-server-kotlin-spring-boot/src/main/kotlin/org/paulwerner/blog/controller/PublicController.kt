package org.paulwerner.blog.controller

import org.paulwerner.blog.dto.CategoryDto
import org.paulwerner.blog.dto.PostDto
import org.paulwerner.blog.service.BlogService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/public/v1")
class PublicController(private var blogService: BlogService) {

    @GetMapping("/categories")
    fun getAllCategories(): List<CategoryDto> = blogService.findAllCategories()


    @GetMapping("/posts")
    fun getAllPostsByCategory(@RequestParam("category") category: String): List<PostDto> = blogService.findAllPostsByCategory(category)

}