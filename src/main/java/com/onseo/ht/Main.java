package com.onseo.ht;

import com.onseo.ht.service.LoadTestService;

public class Main {
    public static void main(String[] args) {
        new LoadTestService().doLoadTesting();
    }
}
