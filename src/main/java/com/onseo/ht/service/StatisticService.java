package com.onseo.ht.service;

import com.onseo.ht.data.ThreadState;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StatisticService {

    private AtomicInteger availableThreads;
    private Long startTime;
    private Long finishTime;
    private List<Integer> allResponses = new LinkedList<>();

    private PrintService printService;

    public StatisticService(Integer availableThreads) {
        this.availableThreads = new AtomicInteger(availableThreads);
        this.printService = new PrintService();
    }


    public synchronized void reportFinish(ThreadState state) {
        if (startTime == null) {
            startTime = state.getStartTime();
        }

        allResponses.addAll(state.getResponses());

        if (availableThreads.decrementAndGet() == 0 ) { //all threads have finished
            finishTime = state.getFinishTime();
            generateAndPrintStatistic();
        }
        state.getCurrentVertx().close();

    }

    private void generateAndPrintStatistic() {
        Integer time = Math.toIntExact((finishTime - startTime) / 1000);
        if (time < 1) {
            time = 1;
        }
        Integer count = allResponses.size();
        Integer countPerSecond = count / time;
        printService.print(count, time, countPerSecond, allResponses);
    }
}
