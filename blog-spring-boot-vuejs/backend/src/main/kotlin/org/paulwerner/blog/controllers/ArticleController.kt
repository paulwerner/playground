package org.paulwerner.blog.controllers

import org.paulwerner.blog.dto.ArticleDto
import org.paulwerner.blog.services.ArticleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/blog/articles")
class ArticleController(private val articleService: ArticleService) {

    @GetMapping
    fun getAllArticles(): ResponseEntity<List<ArticleDto>> =
            ResponseEntity
                    .ok()
                    .body(articleService.getAllArticles())

    @GetMapping("/category/{category}")
    fun getArticlesByCategory(@PathVariable category: String): ResponseEntity<List<ArticleDto>> =
            ResponseEntity
                    .ok()
                    .body(articleService.getArticlesByCategory(category))

    @GetMapping("/{slug}")
    fun getBySlug(@PathVariable slug: String): ResponseEntity<ArticleDto> =
            ResponseEntity
                    .ok()
                    .body(articleService.getArticleBySlug(slug))

    @PostMapping
    fun createArticle(@RequestBody articleDto: ArticleDto): ResponseEntity<ArticleDto> =
            ResponseEntity
                    .ok()
                    .body(articleService.createArticle(articleDto))

    @PutMapping("/{slug}")
    fun updateArticle(@PathVariable slug: String, @RequestBody articleDto: ArticleDto): ResponseEntity<String> =
            ResponseEntity
                    .ok()
                    .body(articleService.updateArticle(slug, articleDto))

}
