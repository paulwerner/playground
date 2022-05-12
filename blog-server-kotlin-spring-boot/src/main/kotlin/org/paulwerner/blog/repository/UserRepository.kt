package org.paulwerner.blog.repository

import org.paulwerner.blog.model.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {

    fun findByEmail(email: String): User?

}