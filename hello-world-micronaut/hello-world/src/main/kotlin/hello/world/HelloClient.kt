package hello.world

import io.micronaut.http.client.annotation.Client

@Client("/hello")
interface HelloClient : HelloApi
