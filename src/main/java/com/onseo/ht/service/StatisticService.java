package com.onseo.ht.service;

import java.util.LinkedList;
import java.util.List;

import static com.onseo.ht.util.Const.AVAILABLE_THREADS;
import static com.onseo.ht.util.Const.BAD_RESPONSE;
import static com.onseo.ht.util.Const.SUCCESS_RESPONSE;
import static com.onseo.ht.util.ProgramState.*;

public class StatisticService {

    private static List<Integer> allResponses = new LinkedList<>();

    public synchronized static void writeStatistic(List<Integer> responses) {
        setFinishedThreads(getFinishedThreads() + 1);
        allResponses.addAll(responses);

        if (getFinishedThreads().equals(AVAILABLE_THREADS)) {

            Long time = (System.currentTimeMillis() - getStartTime()) / 1000;
            Integer count = allResponses.size();
            Long countPerSecond = count / time;


            String result = "All threads = " + AVAILABLE_THREADS + "\n" +
                    "All requests = " + count + "\n" +
                    "Time = " + time + "\n" +
                    "Requests per second " + countPerSecond + "\n" +
                    "SUCCESS responses: " + allResponses.stream().filter(r -> r.equals(SUCCESS_RESPONSE)).count() + "\n" +
                    "FAIL responses: " + allResponses.stream().filter(r -> r.equals(BAD_RESPONSE)).count();

            System.out.println(result);
            closeVertexes();
        }
    }
}
