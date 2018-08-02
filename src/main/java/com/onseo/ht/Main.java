package com.onseo.ht;

import com.onseo.ht.service.LoadTestService;
import com.onseo.ht.service.PropertiesService;

public class Main {

    private static final Integer AVAILABLE_THREADS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        new LoadTestService(PropertiesService.getInstance(), AVAILABLE_THREADS).doLoadTesting();
    }
}
