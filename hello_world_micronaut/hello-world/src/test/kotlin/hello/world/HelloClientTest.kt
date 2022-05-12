package hello.world

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions.assertEquals

object HelloClientTest : Spek({

    describe("HelloClient TestSuite") {
        val server = ApplicationContext.run(EmbeddedServer::class.java)
        val helloClient = server.applicationContext.getBean(HelloClient::class.java)

        it("should return greeting when requesting index with parameter name") {
            val response = helloClient.index("Paul")
            assertEquals("Hello Paul!", response.greeting)
        }
        it("should return greeting when requesting reactive with parameter name") {
            val response = helloClient.reactive("Paul")
            assertEquals("Hello Paul!", response.blockingGet().greeting)
        }

        afterGroup {
            server.stop()
        }
    }

})
