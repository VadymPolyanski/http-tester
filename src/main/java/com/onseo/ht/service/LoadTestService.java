package com.onseo.ht.service;

import com.onseo.ht.data.ThreadState;
import com.onseo.ht.vertx.VertxThread;

import javax.net.ssl.SSLContext;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class LoadTestService {


    private Integer availableThreads;

    public LoadTestService(PropertiesService propertiesService, Integer availableThreads) {
        this.propertiesService = propertiesService;
        this.availableThreads = availableThreads;
    }

    private PropertiesService propertiesService;

    public void doLoadTesting() {

        ExecutorService testExecutor = Executors.newFixedThreadPool(availableThreads);


        int rpsForOneThread = propertiesService.getRps() / availableThreads; //divide all rps requests for all threads
        String url = propertiesService.gerURL();
        Integer maxPoolSize = propertiesService.gerMaxPoolSize();

        StatisticService.init(availableThreads);

        IntStream.range(0, availableThreads)
                .forEach( i -> testExecutor.execute(
                        new VertxThread(new ThreadState(rpsForOneThread, url, maxPoolSize))));

        testExecutor.shutdown();
    }
}
