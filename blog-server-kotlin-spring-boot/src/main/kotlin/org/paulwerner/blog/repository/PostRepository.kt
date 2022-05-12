package org.paulwerner.blog.repository

import org.paulwerner.blog.model.Category
import org.paulwerner.blog.model.Post
import org.springframework.data.mongodb.repository.MongoRepository

interface PostRepository : MongoRepository<Post, String> {

    fun findByCategory(category: Category): List<Post>

}