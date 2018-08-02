package com.onseo.ht.vertx;

import com.onseo.ht.data.ThreadState;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;

import java.util.stream.LongStream;

import static com.onseo.ht.data.Const.BAD_RESPONSE;

public class VertxHttpClient extends AbstractVerticle {

    private final ThreadState threadState;

    private HttpClientOptions options;

    private HttpClient httpClient;

    VertxHttpClient(ThreadState threadState) {
        super();
        this.threadState = threadState;

        options = new HttpClientOptions()
                .setKeepAlive(true)
                .setMaxPoolSize(threadState.getMaxPoolSize());
    }

    @Override
    public void start() {
        httpClient = vertx.createHttpClient(options);

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