package com.onseo.ht;

import com.onseo.ht.service.LoadTestService;
import com.onseo.ht.service.PropertiesService;

public class Main {


    public static void main(String[] args) {
        new LoadTestService(new PropertiesService()).doLoadTesting();
    }
}
