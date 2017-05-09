package com.flex.service;

/**
 * Created by jasonskipper on 5/8/17.
 */
public interface MetricServiceInterface {
    void addMetric(String key);

    void addValueToMetric(String key, Double aMetricValue);

    Double getMean(String key);

    Double getMedian(String key);

    Double getMin(String key);

    Double getMax(String key);

    boolean exists(String key);

    void clear();
}
