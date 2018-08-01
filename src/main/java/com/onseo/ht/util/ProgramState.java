package com.onseo.ht.util;


import io.vertx.core.Vertx;

import java.util.ArrayList;
import java.util.List;

import static com.onseo.ht.util.Const.AVAILABLE_THREADS;

public class ProgramState {
    private static Integer finishedThreads = 0;
    private static Long startTime;
    private static List<Vertx> vertxes = new ArrayList<>(AVAILABLE_THREADS);


    public static Integer getFinishedThreads() {
        return finishedThreads;
    }

    public static void incrementFinishedThreads() {
        finishedThreads = finishedThreads + 1;
    }

    public static Long getStartTime() {
        return startTime;
    }

    public static void writeStartTime() {
        ProgramState.startTime = System.currentTimeMillis();
    }

    public static void addVertx(Vertx vertx) {
        vertxes.add(vertx);
    }

    public static void closeVertexes() {
        vertxes.forEach(Vertx::close);
    }
}
