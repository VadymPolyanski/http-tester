package com.onseo.ht.service;

import com.onseo.ht.vertx.VertxThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static com.onseo.ht.util.Const.AVAILABLE_THREADS;
import static com.onseo.ht.util.Const.REQUESTS_PER_SECOND;
import static com.onseo.ht.util.ProgramState.writeStartTime;

public class LoadTestService implements ILoadTestService {

    @Override
    public void doLoadTesting() {

        ExecutorService testExecutor = Executors.newFixedThreadPool(AVAILABLE_THREADS);

        int requestsCountForOneThread = REQUESTS_PER_SECOND / AVAILABLE_THREADS;

        writeStartTime();

        IntStream.range(0, AVAILABLE_THREADS)
                .forEach( i -> testExecutor.execute(new VertxThread(requestsCountForOneThread)));

        testExecutor.shutdown();
    }
}
