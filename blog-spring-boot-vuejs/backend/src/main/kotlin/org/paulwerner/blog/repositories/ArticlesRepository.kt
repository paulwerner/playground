package org.paulwerner.blog.repositories

import org.bson.types.ObjectId
import org.paulwerner.blog.models.Article
import org.paulwerner.blog.models.Category
import org.springframework.data.mongodb.repository.MongoRepository

interface ArticlesRepository : MongoRepository<Article, ObjectId> {

    fun findBySlug(slug: String): Article?

    fun findByCategoriesContaining(category: Category): List<Article>

}
