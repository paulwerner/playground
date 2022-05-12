package org.paulwerner.blog.repository

import org.paulwerner.blog.model.Category
import org.springframework.data.mongodb.repository.MongoRepository

interface CategoryRepository : MongoRepository<Category, String> {

    fun findByTitle(name: String): Category?

}