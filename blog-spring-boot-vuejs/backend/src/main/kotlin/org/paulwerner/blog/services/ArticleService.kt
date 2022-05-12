package org.paulwerner.blog.services

import com.github.slugify.Slugify
import org.paulwerner.blog.dto.ArticleDto
import org.paulwerner.blog.exceptions.AlreadyExistsException
import org.paulwerner.blog.exceptions.NotFoundException
import org.paulwerner.blog.mappers.toArticleDto
import org.paulwerner.blog.models.Article
import org.paulwerner.blog.repositories.ArticlesRepository
import org.springframework.stereotype.Service

@Service
class ArticleService(
        private val articlesRepository: ArticlesRepository,
        private val categoryService: CategoryService
) {

    private val slugify = Slugify()


    fun createArticle(createArticleDto: ArticleDto): ArticleDto {
        val slug = getValidSlug(createArticleDto)
        val categoryNames = createArticleDto.categories.map { it.name }.toList()
        val categories =
                if (categoryNames.isEmpty())
                    arrayListOf()
                else
                    categoryService.getByNameIn(categoryNames)
        val article = Article(
                slug = slug,
                title = createArticleDto.title,
                author = createArticleDto.author,
                intro = createArticleDto.intro,
                content = createArticleDto.content,
                categories = categories
        )
        val savedArticle = articlesRepository.save(article)

        return toArticleDto(savedArticle)
    }

    fun getAllArticles(): List<ArticleDto> =
            articlesRepository.findAll()
                    .map { toArticleDto(it) }
                    .toList()

    fun getArticlesByCategory(categoryName: String): List<ArticleDto> {
        val category = categoryService.getByName(categoryName)
        return articlesRepository.findByCategoriesContaining(category)
                .map { toArticleDto(it) }
                .toList()
    }

    fun getBySlug(slug: String): Article =
            articlesRepository.findBySlug(slug)
                    ?: throw NotFoundException("Article with slug=[$slug] could not be found")

    fun getArticleBySlug(slug: String): ArticleDto {
        val article = getBySlug(slug)
        return toArticleDto(article)
    }

    fun updateArticle(slug: String, articleDto: ArticleDto): String {
        val article = getBySlug(slug)
        val categories = categoryService.getByNameIn(
                articleDto.categories
                        .map { categoryDto -> categoryDto.name }
        )

        article.slug = slugify.slugify(articleDto.title)
        article.title = articleDto.title
        article.author = articleDto.author
        article.content = articleDto.content
        article.categories = categories

        articlesRepository.save(article)

        return article.slug
    }

    private fun getValidSlug(createArticleDto: ArticleDto): String {
        val slug = slugify.slugify(createArticleDto.title)
        articlesRepository.findBySlug(slug) ?: return slug
        throw AlreadyExistsException("Article with slug=[$slug] already exists")
    }

}
