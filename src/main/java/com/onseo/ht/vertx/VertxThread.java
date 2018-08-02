package com.onseo.ht.vertx;

import com.onseo.ht.data.ThreadState;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;


import java.util.LinkedList;
import java.util.List;
import java.util.stream.LongStream;

import static com.onseo.ht.data.Const.BAD_RESPONSE;


public class VertxThread implements Runnable {

    private ThreadState threadState;
    private Vertx vertx;
    private HttpClient httpClient;

    public VertxThread(ThreadState threadState) {
        this.threadState = threadState;
        vertx = threadState.getCurrentVertx();

        HttpClientOptions httpClientOptions = new HttpClientOptions()
                .setKeepAlive(true)
                .setConnectTimeout(2000)
                .setMaxPoolSize(threadState.getMaxPoolSize());

        this.httpClient = vertx.createHttpClient(httpClientOptions);
    }

    @Override
    public void run() {
        vertx.deployVerticle(new VertxHttpClient());
    }

    private class VertxHttpClient extends AbstractVerticle {


        @Override
        public void start() {
            threadState.setStartTime(System.currentTimeMillis());

            LongStream.range(0, threadState.getRps()).forEach(i -> //send requests in loop
                    httpClient.getAbs(threadState.getUrl(), httpClientResponse -> threadState.addResponse(httpClientResponse.statusCode()))

                            .exceptionHandler(throwable -> threadState.addResponse(BAD_RESPONSE))
                            .end());
        }

        @Override
        public void stop() throws Exception {
            super.stop();
            httpClient.close();
        }
    }
}
