package org.paulwerner.blog.repositories

import org.bson.types.ObjectId
import org.paulwerner.blog.models.Category
import org.springframework.data.mongodb.repository.MongoRepository

interface CategoriesRepository : MongoRepository<Category, ObjectId> {

    fun findByName(name: String): Category?

    fun findByNameIn(names: List<String>): List<Category>

}
