package com.onseo.ht.service;

import com.onseo.ht.vertx.VertxThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static com.onseo.ht.util.Const.AVAILABLE_THREADS;
import static com.onseo.ht.util.Const.REQUESTS_PER_SECOND;
import static com.onseo.ht.util.ProgramState.setStartTime;

public class LoadTestService implements ILoadTestService {

    @Override
    public void doLoadTesting() {

        ExecutorService threadExecutor = Executors.newFixedThreadPool(AVAILABLE_THREADS);

        int requestsCountForOneThread = REQUESTS_PER_SECOND / AVAILABLE_THREADS;

        setStartTime(System.currentTimeMillis());

        IntStream.range(0, AVAILABLE_THREADS + 1)
                .forEach( i -> threadExecutor.execute(new VertxThread(requestsCountForOneThread)));

        threadExecutor.shutdown();
    }
}
