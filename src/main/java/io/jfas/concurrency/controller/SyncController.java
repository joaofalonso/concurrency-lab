package io.jfas.concurrency.controller;

import io.jfas.concurrency.usecase.SyncUseCase;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@AllArgsConstructor
public class SyncController {

    private final SyncUseCase syncUseCase;

    @Get("/sync")
    public HttpResponse<?> asyncGet(){
        return HttpResponse.ok(this.syncUseCase.execute());
    }

}
