package com.onseo.ht.service;

import com.onseo.ht.data.ThreadState;
import com.onseo.ht.vertx.VertxThread;
import io.vertx.core.Vertx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class LoadTestService {

    private static final Integer AVAILABLE_THREADS = Runtime.getRuntime().availableProcessors();
    private static final Integer INTERVAL = 1;

    public LoadTestService(PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }

    private PropertiesService propertiesService;

    public void doLoadTesting() {
        int rpsForOneThread = propertiesService.getRps() / AVAILABLE_THREADS; //divide all rps requests for all threads
        String url = propertiesService.gerURL();
        Integer maxPoolSize = propertiesService.gerMaxPoolSize();



        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        service.scheduleAtFixedRate(() -> {
            StatisticService.init(AVAILABLE_THREADS);
            ExecutorService testExecutor = Executors.newFixedThreadPool(AVAILABLE_THREADS);

            IntStream.range(0, AVAILABLE_THREADS)
                    .forEach(i -> testExecutor.execute(
                            new VertxThread(new ThreadState(rpsForOneThread, url, maxPoolSize, Vertx.vertx()))));

            testExecutor.shutdown();
        }, 0, INTERVAL, TimeUnit.SECONDS);
    }
}
