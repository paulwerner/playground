# Micronaut basics and Hello World example

## Scaffolding

Micronaut comes with a convenient CLI scaffolding tool. It can be used as CLI tool 

```
mn help
mn help create-app

```
or in interactive mode.

```
$ mn
| Starting interactive mode...
| Enter a command name to run. Use TAB for completion:
mn> help
...
mn> help create-app
...
```

### creating a new project
As a polyglot framework Micronaut supports Java, Groovy and Kotlin as first-class citizens. 
For projects builds it supports gradle and maven. Using the CLI tool we can easily setup a new projects.
```
mn create-app --lang kotlin --build gradle hello-world
```
As default the server listen on a random port but can be configured in the *src/main/resources/application.yml* by adding the following 
config:
```
micronaut:
    server:
        port: 8080
```
The application is ready to run:
```
cd mn-hello-world
./gradlew run
> Task :run
13:02:14.933 [main] INFO  io.micronaut.runtime.Micronaut - Startup completed in 809ms. Server Running: http://localhost:8080
<===========--> 85% EXECUTING [1m 8s]
```
Calling the API should gives us a response from the running application on the server.
```
url http://localhost:8080
{"_links":{"self":{"href":"/","templated":false}},"message":"Page Not Found"}
```
#### creating components
The CLI tool can also be used to create components like controllers and clients along with their test classes. 
```
mn create-controller HelloControler
| Rendered template Controller.kt to destination src/main/kotlin/hello/world/HelloController.kt
| Rendered template ControllerTest.kt to destination src/test/kotlin/hello/world/HelloControllerTest.kt
```
Following Test-Driven Development it auto generates a test for the new controller as well.

To create a client the following CLI command can be used:
``` 
mn create-client HelloClient
| Rendered template Client.kt to destination src/main/kotlin/hello/world/HelloClient.kt
```

Both, the controller and the client, can be defined using an interface. This interface provides definition about the used HTTP method, 
the used parameters and MediaTypes. Returned POJOs will be converted to JSONs. Then the controller on the one hand has to implement the 
interface's methods whereas the client has to extend it.

Micronaut uses RxJava2 with Single and Observable as implementation for the Reactive Streams API but can be changed to Reactor as well to 
use Mono and Flux types.

HelloApi.kt
```kotlin
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.reactivex.Single

interface HelloApi {

    @Get("/")
    fun index(@QueryValue("name") name: String): HelloMessage

    @Get("/reactive")
    fun reactive(@QueryValue("name") name: String): Single<HelloMessage>

}
```

HelloController.kt
```kotlin
import io.micronaut.http.annotation.Controller
import io.reactivex.Single

@Controller("/hello")
class HelloController : HelloApi {

    override fun index(name: String): HelloMessage =
        HelloMessage("Hello $name!")


    override fun reactive(name: String): Single<HelloMessage> =
        Single.just(HelloMessage("Hello $name!"))

}
```

HelloClient.kt
```kotlin
import io.micronaut.http.client.annotation.Client

@Client("/hello")
interface HelloClient : HelloApi
```

The concrete component for the client will be created during compile time.

## References
[codecentric Blog](https://blog.codecentric.de/en/2019/01/micronaut-microservices/)
