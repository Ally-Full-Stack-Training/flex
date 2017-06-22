package com.flex.service;

/**
 * This is the interface we will use to track and calculate stats
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
