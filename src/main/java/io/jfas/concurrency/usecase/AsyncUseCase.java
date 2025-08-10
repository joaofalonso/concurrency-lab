package io.jfas.concurrency.usecase;

import jakarta.inject.Singleton;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.*;

@Singleton
@NoArgsConstructor
public class AsyncUseCase {

    public String execute(){
        System.out.println("Async exec...");
        Instant start = Instant.now();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        System.out.println(String.format("Loop quantity: %s", 1000));
        ArrayList<CompletableFuture> completableFutures = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int value = i;
            CompletableFuture<Double> doubleCompletableFuture = CompletableFuture.supplyAsync(() -> getValue(Integer.valueOf(value)), executorService);
            completableFutures.add(doubleCompletableFuture);
        }

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]));
        allFutures.join();
        float millisecondsDifference = java.time.Duration.between(start, Instant.now()).toMillis();
        String msg = String.format("Process time: %s", millisecondsDifference);
        System.out.println(msg);
        return  msg;
    }

    private Double getValue(int value){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Math.random() * value;
    }

}
