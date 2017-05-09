package com.flex.service;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;


/**
 * Created by jasonskipper on 5/8/17.
 */
@Service
public class MetricService implements MetricServiceInterface {


    private HashMap<String, DescriptiveStatistics> metricCatalogue = new HashMap();

    @Override
    public void addMetric(String key){

        metricCatalogue.computeIfAbsent(key, k -> new DescriptiveStatistics());
    }

    @Override
    public void addValueToMetric(String key, Double aMetricValue){
        metricCatalogue.computeIfPresent(key, (k,v) -> {
            v.addValue(aMetricValue);
            return v;
        } );
    }

    @Override
    public Double getMean(String key){
        DescriptiveStatistics treeOfMetricValues = metricCatalogue.get(key);
        DescriptiveStatistics data = metricCatalogue.get(key);
        if(data!=null){
            return data.getMean();
        }else{
            return null;
        }
    }

    @Override
    public Double getMedian(String key){
        DescriptiveStatistics data = metricCatalogue.get(key);
        if(data!=null){
            return data.getPercentile(50);
        }else{
            return null;
        }    }

    @Override
    public Double getMin(String key){
        DescriptiveStatistics data = metricCatalogue.get(key);
        if(data!=null){
            return data.getMin();
        }else{
            return null;
        }    }

    @Override
    public Double getMax(String key){
        DescriptiveStatistics data = metricCatalogue.get(key);
        if(data!=null){
            return data.getMax();
        }else{
            return null;
        }
    }

    @Override
    public void clear() {
        metricCatalogue.clear();
    }
}
