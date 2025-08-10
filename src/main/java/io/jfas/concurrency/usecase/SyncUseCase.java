package io.jfas.concurrency.usecase;

import jakarta.inject.Singleton;

import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Singleton
public class SyncUseCase {

    public String execute(){
        System.out.println("Sync exec...");
        Instant start = Instant.now();
        System.out.println(String.format("Loop quantity: %s", 1000));
        for (int i = 0; i < 1000; i++) {
            getValue(i);
        }
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
