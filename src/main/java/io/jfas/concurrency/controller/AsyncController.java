package io.jfas.concurrency.controller;

import io.jfas.concurrency.controller.dto.AsyncPostRequest;
import io.jfas.concurrency.usecase.AsyncUseCase;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AsyncController {

    private final AsyncUseCase asyncUseCase;
    @Get("/async")
    public HttpResponse<?> asyncGet(){
        return HttpResponse.ok(this.asyncUseCase.execute());
    }

    @Post(value = "/async", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<?> asyncPost(@Body AsyncPostRequest asyncPostRequest){
        String responseMsg = this.asyncUseCase.execute(asyncPostRequest.loops(), asyncPostRequest.threadPoolSize());
        return HttpResponse.ok(responseMsg);
    }
}
