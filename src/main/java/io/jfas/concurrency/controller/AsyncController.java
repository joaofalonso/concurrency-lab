package io.jfas.concurrency.controller;

import io.jfas.concurrency.usecase.AsyncUseCase;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AsyncController {

    private final AsyncUseCase asyncUseCase;
    @Get("/async")
    public HttpResponse<?> asyncGet(){
        return HttpResponse.ok(this.asyncUseCase.execute());
    }
}
