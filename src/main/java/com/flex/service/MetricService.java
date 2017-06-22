package com.flex.service;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.stereotype.Service;

import java.util.HashMap;


/**
 * Service which implements the MetricServiceInterface using apache commons math3 lib.
 */
@Service
public class MetricService implements MetricServiceInterface {


    private HashMap<String, DescriptiveStatistics> metricCatalogue = new HashMap();

    /**
     * Return true if this stat exists within our hash
     * @param key
     * @return
     */
    @Override
    public boolean exists(String key) {
        return metricCatalogue.containsKey(key);
    }

    /**
     * Create a new descriptive stat object and place it into the hash if it does not exist.
     * If it exists then do nothing (no need to add this since we already have it)
     * @param key
     */
    @Override
    public void addMetric(String key){

        metricCatalogue.computeIfAbsent(key, k -> new DescriptiveStatistics());
    }

    /**
     * If we have this metric present in our hash then add this vaule to the descriptive stats object
     * @param key
     * @param aMetricValue
     */
    @Override
    public void addValueToMetric(String key, Double aMetricValue){
        metricCatalogue.computeIfPresent(key, (k,v) -> {
            v.addValue(aMetricValue);
            return v;
        } );
    }

    /**
     * Get the mean for the given metric
     * @param key
     * @return
     */
    @Override
    public Double getMean(String key){
        DescriptiveStatistics data = metricCatalogue.get(key);
        if(data!=null){
            return data.getMean();
        }else{
            return null;
        }
    }

    /**
     * Get the Media for the given metric
     * @param key name for the metric of which you want the median
     * @return
     */
    @Override
    public Double getMedian(String key){
        DescriptiveStatistics data = metricCatalogue.get(key);
        if(data!=null){
            return data.getPercentile(50);
        }else{
            return null;
        }    }

    /**
     * Get the min for the given metric
     * @param key name for the metric of which you want the max
     * @return
     */
    @Override
    public Double getMin(String key){
        DescriptiveStatistics data = metricCatalogue.get(key);
        if(data!=null){
            return data.getMin();
        }else{
            return null;
        }    }

    /**
     * Get the max for the given metric
     * @param key name for the metric of which you want the max
     * @return
     */
    @Override
    public Double getMax(String key){
        DescriptiveStatistics data = metricCatalogue.get(key);
        if(data!=null){
            return data.getMax();
        }else{
            return null;
        }
    }

    /**
     * Clear out all metrics
     */
    @Override
    public void clear() {
        metricCatalogue.clear();
    }
}
