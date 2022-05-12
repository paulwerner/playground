package org.paulwerner.blog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing

@SpringBootApplication
@EnableMongoAuditing
class BlogApplication

fun main(args: Array<String>) {
    runApplication<BlogApplication>(*args)
}
