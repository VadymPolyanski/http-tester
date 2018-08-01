package com.onseo.ht.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.LongStream;

import static com.onseo.ht.service.StatisticService.setStatistic;
import static com.onseo.ht.util.Const.BAD_RESPONSE;
import static com.onseo.ht.util.Const.MAX_POOL_SIZE;
import static com.onseo.ht.util.Const.URL;

public class VertxHttpClient extends AbstractVerticle {

    private final int requestsPerSecond;
    private final List<Integer> responses = new LinkedList<>();
    private final HttpClientOptions options= new HttpClientOptions()
            .setKeepAlive(true)
            .setMaxPoolSize(MAX_POOL_SIZE);
    private HttpClient httpClient;

    VertxHttpClient(int requestsPerSecond) {
        super();
        this.requestsPerSecond = requestsPerSecond;
    }

    @Override
    public void start() {
        httpClient = vertx.createHttpClient(options);

        LongStream.range(0, requestsPerSecond).forEach(i ->
                httpClient.getAbs(URL, httpClientResponse -> responses.add(httpClientResponse.statusCode()))
                        .exceptionHandler(throwable -> responses.add(BAD_RESPONSE))
                        .endHandler(handler -> isThreadFinished())
                        .end());
    }

    private void isThreadFinished() {
        if (responses.size() == requestsPerSecond)
            setStatistic(responses);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        httpClient.close();
    }
}