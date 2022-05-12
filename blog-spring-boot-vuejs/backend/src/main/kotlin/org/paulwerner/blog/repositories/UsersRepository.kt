package org.paulwerner.blog.repositories

import org.paulwerner.blog.models.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UsersRepository : MongoRepository<User, String> {

    fun findByUsername(username: String): User?

}
