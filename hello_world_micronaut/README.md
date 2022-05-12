# Learning Micronaut

## About
The purpose of this project is to keep the notes I take and code snippets I create while studying the 
[Micronaut Framework](https://micronaut.io).

Each directory is a sub project on it's own covering a specific use case. 

Feel free to use whatever you might find useful.

## Introduction to Micronaut

Micronaut is "a modern, JVM-based, full-stack framework for building modular, easily testable microservice and serverless applications." 
[micronaut.io](https://micronaut.io). As a polyglot framework it can be used with Java, Groovy or Kotlin. Besides other IoC frameworks 
it features a Dependency Injection and Aspect-Oriented Programming runtime that uses no reflection. Because of it's nature it's start up 
time and memory consumption is not bound to the size of the codebase and gives it a monumental leap in startup time, a blazing fast 
throughput with a minimal memory footprint.

### Features

#### Micronaut for GraalVM
Micronaut apps startup in tens of milliseconds with GraalVM, a new universal virtual machine from Oracle that supports a polyglot runtime
 environment and the ability to compile Java applications down to native machine code.

#### declarative, reactive, compile-time http client
Building reactive clients during compile-time to reduce memory consumption.

```
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.Client;
import io.reactivex.Single;

@Client("/hello")
public interface HelloClient {

    @Get("/")
    Single hello();
}
```

#### non-blocking http server built in on netty
Easy to expose APIs that can be consumed by HTTP clients.

```
import io.micronaut.http.annotation.*;

@Controller("/hello") 
public class HelloController {

    @Get("/") 
    public String index() {
        return "Hello World"; 
    }
}

```

#### fast and easy testing
Allows to spin up servers and clients in unit test easily and run them instantaneously.

```
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.*

class HelloClientSpec extends Specification {
    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer =
        ApplicationContext.run(EmbeddedServer)

    @Shared
    HelloClient client = embeddedServer
        .applicationContext
        .getBean(HelloClient)

    void "test hello world response"() {
        expect:
        client.hello().blockingGet() == "Hello World"
    }
}

```

#### efficient compile-time dependency injection and AOP
Provides a simple compile-time aspect-oriented programming API that does not use reflections.

```
@Scheduled(fixedRate = "5m")
@Retry(attempts='5')
void everyFiveMinutes() {
    messageService.sendMessage("Hello World");
}
```

#### build fully reactive and non-blocking apps
Supports any framework that implements Reactive Streams, including RxJava, and Reactor.

```
@Client( id = "person-service" )
public interface PersonClient {
    public Single<Person>
        save(@Body Single<Person>person)
}

```

#### natively cloud native
Includes support for common discovery services, distributed tracing tools, and cloud runtimes.

#### ready to develop serverless applications
With the low overhead compile-time DI and AOP it makes it perfect for writing functions for serverless environments like 
[AWS Lambda](https://aws.amazon.com/lambda/).

```
@Field
@Inject
HelloService helloService

Message hello(Person person) {
    helloService.hello(person)
}
```
#### designed for building resilient microservices

``` 
import io.micronaut.retry.annotation.*

@CircuitBreaker(reset = "30s")
public List findBooks() {
    ...
    ..
}
```
#### fast data-access configuration
Provides sensible defaults that automatically configure your favourite data access toolkit and APIs to make it easy to write your own 
integrations.


## Outlook
* Reactive programming
* Database access with GORM
* Service discovery
* Serverless applications
* Distributed tracing
* Cron jobs
* Circuit breaker

## References
[Micronaut Framework](https://micronaut.io)
