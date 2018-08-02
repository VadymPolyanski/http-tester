package com.onseo.ht.service;

import java.util.List;

import static com.onseo.ht.data.Const.BAD_RESPONSE;
import static com.onseo.ht.data.Const.SUCCESS_RESPONSE;

class PrintService {
    void print(Integer count, Integer time, Integer countPerSecond, List<Integer> allResponses) {
        String result = "All requests = " + count + "\n" +
                "Time = " + time + "\n" +
                "Requests per second " + countPerSecond + "\n" +
                "SUCCESS responses: " + allResponses.stream().filter(SUCCESS_RESPONSE::equals).count() + "\n" +
                "FAIL responses: " + allResponses.stream().filter(BAD_RESPONSE::equals).count() + "\n\n\n";

        System.out.println(result);
    }
}
