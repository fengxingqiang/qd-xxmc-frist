package com.qd.xxmc.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class MultiThreadService {

    public void multiThread(List<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        Executor executor = Executors.newFixedThreadPool(2, r -> {
            Thread thread = new Thread((r));
            thread.setDaemon(true);
            return thread;
        });

        List<CompletableFuture<Void>> futures = list.stream().map(str -> test(str, executor)).collect(Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[list.size()])).join(); //等待所有线程任务结束
        System.out.println("程序结束");
    }

    public CompletableFuture<Void> test(String str, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(str);
            return null;
        }, executor);
    }
}
