package com.onseo.ht.vertx;

import com.onseo.ht.data.ThreadState;
import io.vertx.core.Vertx;


public class VertxThread implements Runnable {

    private ThreadState threadState;
    public VertxThread(ThreadState threadState) {
        this.threadState = threadState;
    }

    @Override
    public void run() {
        Vertx vertx = Vertx.vertx();
        threadState.setCurrentVertx(vertx);
        vertx.deployVerticle(new VertxHttpClient(threadState));
    }
}
