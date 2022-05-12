package org.paulwerner.blog.mappers

import org.paulwerner.blog.dto.ArticleDto
import org.paulwerner.blog.dto.CategoryDto
import org.paulwerner.blog.models.Article
import org.paulwerner.blog.models.Category


fun toArticleDto(article: Article) =
        ArticleDto(
                slug = article.slug,
                title = article.title,
                author = article.author,
                intro = article.intro,
                content = article.content,
                createdDate = article.createdDate,
                categories = toCategoryDtos(article.categories)
        )

fun toCategoryDto(category: Category) =
        CategoryDto(name = category.name)

private fun toCategoryDtos(categories: List<Category>) =
        categories.map { toCategoryDto(it) }.toList()
