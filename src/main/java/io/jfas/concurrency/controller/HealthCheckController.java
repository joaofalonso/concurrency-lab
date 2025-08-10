package io.jfas.concurrency.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/")
public class HealthCheckController {

    @Get("/")
    public HttpResponse<?> ok(){
        return HttpResponse.ok("Hi");
    }
}
