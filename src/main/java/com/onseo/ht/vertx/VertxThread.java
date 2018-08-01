package com.onseo.ht.vertx;

import io.vertx.core.Vertx;

import static com.onseo.ht.util.ProgramState.addVertx;

public class VertxThread implements Runnable {

    private final int requestsPerSecond;
    public VertxThread(int requestsPerSecond) {
        this.requestsPerSecond = requestsPerSecond;
    }

    @Override
    public void run() {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new VertxHttpClient(requestsPerSecond));
        addVertx(vertx);
    }
}
