package com.onseo.ht.service;

import com.onseo.ht.data.ThreadState;

import java.util.LinkedList;
import java.util.List;

import static com.onseo.ht.data.Const.BAD_RESPONSE;
import static com.onseo.ht.data.Const.SUCCESS_RESPONSE;

public class StatisticService {

    private Integer availableThreads;
    private Long startTime;
    private List<Integer> allResponses = new LinkedList<>();

    private static StatisticService instance;

    private StatisticService(Integer availableThreads) {
        this.availableThreads = availableThreads;
    }


    public synchronized void reportFinish(ThreadState state) {
        availableThreads--;

        if (startTime == null) {
            startTime = state.getStartTime();
        }

        allResponses.addAll(state.getResponses());

        if (availableThreads.equals(0)) { //all threads have finished
            generateAndPrintStatistic(state.getFinishTime());
        }
        state.getCurrentVertx().close();

    }

    private void generateAndPrintStatistic(long finishTime) {
        Long time = (finishTime - startTime) / 1000;
        Integer count = allResponses.size();
        Long countPerSecond = count / time;

        String result = "All threads = " + availableThreads + "\n" +
                "All requests = " + count + "\n" +
                "Time = " + time + "\n" +
                "Requests per second " + countPerSecond + "\n" +
                "SUCCESS responses: " + allResponses.stream().filter(r -> r.equals(SUCCESS_RESPONSE)).count() + "\n" +
                "FAIL responses: " + allResponses.stream().filter(r -> r.equals(BAD_RESPONSE)).count() + "\n\n\n";

        System.out.println(result);
    }

    public static StatisticService init(Integer availableThreads) {
        instance = new StatisticService(availableThreads);
        return instance;
    }

    public static StatisticService getInstance() {
        if (instance == null) {
            return init(Runtime.getRuntime().availableProcessors()); // bad case
        }
        return instance;
    }

}
