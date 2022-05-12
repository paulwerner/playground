package hello.world

import io.micronaut.context.ApplicationContext
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions.assertEquals

class HelloControllerTest : Spek({

    describe("HelloController TestSuite") {
        val server = ApplicationContext.run(EmbeddedServer::class.java)
        val client = HttpClient.create(server.url)

        it("should return greetings for GET request on /hello with query parameter name") {
            val response = client.toBlocking().retrieve("/hello?name=Paul", HelloMessage::class.java)
            assertEquals("Hello Paul!", response.greeting)
        }

        it("should return reactive greetings for GET request on /hello/reactive with query parameter name") {
            val response = client.toBlocking().retrieve("/hello/reactive?name=Paul", HelloMessage::class.java)
            assertEquals("Hello Paul!", response.greeting)
        }

        afterGroup {
            client.close()
            server.close()
        }

    }

})
