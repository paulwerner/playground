package org.paulwerner.blog.config

import com.mongodb.MongoClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoConfiguration

@Configuration
class MongoConfig(
        @Value("\${mongo.database.name}")
        private val databaseName: String,
        @Value("\${mongo.database.host:127.0.0.1}")
        private val host: String,
        @Value("\${mongo.database.port:27017}")
        private val port: Int
) : AbstractMongoConfiguration() {

    override fun mongoClient(): MongoClient = MongoClient(host, port)

    override fun getDatabaseName() = databaseName

}
