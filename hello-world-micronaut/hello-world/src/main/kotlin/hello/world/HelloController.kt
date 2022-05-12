package hello.world

import io.micronaut.http.annotation.Controller
import io.reactivex.Single

@Controller("/hello")
class HelloController : HelloApi {

    override fun index(name: String): HelloMessage =
        HelloMessage("Hello $name!")


    override fun reactive(name: String): Single<HelloMessage> =
        Single.just(HelloMessage("Hello $name!"))

}
