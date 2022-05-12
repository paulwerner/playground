package hello.world

import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.reactivex.Single

interface HelloApi {

    @Get("/")
    fun index(@QueryValue("name") name: String): HelloMessage

    @Get("/reactive")
    fun reactive(@QueryValue("name") name: String): Single<HelloMessage>

}
