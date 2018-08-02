package com.onseo.ht.data;

import com.onseo.ht.service.StatisticService;
import io.vertx.core.Vertx;

import java.util.LinkedList;
import java.util.List;

public class ThreadState {

    private Vertx currentVertx;
    private Integer rps;
    private String url;
    private Integer maxPoolSize;
    private Long finishTime;
    private Long startTime;
    private List<Integer> responses;

    public ThreadState(Integer rps, String url, Integer maxPoolSize) {
        this.rps = rps;
        this.url = url;
        this.maxPoolSize = maxPoolSize;
        this.responses = new LinkedList<>();
    }

    public Vertx getCurrentVertx() {
        return currentVertx;
    }

    public void setCurrentVertx(Vertx currentVertx) {
        this.currentVertx = currentVertx;
    }

    public Integer getRps() {
        return rps;
    }

    public String getUrl() {
        return url;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public List<Integer> getResponses() {
        return responses;
    }

    public void addResponse(Integer response) {
        this.responses.add(response);
        if (responses.size() == rps) {
            finishTime = System.currentTimeMillis();
            finish();
        }
    }

    private void finish() {
        StatisticService.getInstance().reportFinish(this);
    }
}
