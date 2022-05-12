package org.paulwerner.blog.controllers

import org.paulwerner.blog.dto.CategoryDto
import org.paulwerner.blog.services.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/blog/categories")
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping
    fun getAllCategories(): ResponseEntity<List<CategoryDto>> =
            ResponseEntity
                    .ok()
                    .body(categoryService.getAllCategories())

    @GetMapping("/{name}")
    fun getCategoryByName(@PathVariable name: String): ResponseEntity<CategoryDto> =
            ResponseEntity
                    .ok()
                    .body(categoryService.getCategoryByName(name))

    @PostMapping
    fun createCategory(@RequestBody categoryDto: CategoryDto): ResponseEntity<CategoryDto> =
            ResponseEntity
                    .ok()
                    .body(categoryService.createCategory(categoryDto))

}
