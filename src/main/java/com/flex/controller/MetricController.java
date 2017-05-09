package com.flex.controller;

import com.flex.data.Metric;
import com.flex.data.MetricStat;
import com.flex.data.MetricStatType;
import com.flex.service.MetricServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jasonskipper on 5/8/17.
 */
@RestController
@RequestMapping(path = "/flex/")
public class MetricController {

    @Autowired
    private MetricServiceInterface metricService;


    @RequestMapping(value = "metric", method = RequestMethod.POST)
    public void create(@RequestBody String name){
        metricService.addMetric(name);
    }

    @RequestMapping(value = "metric/value", method = RequestMethod.POST)
    public void addValueToMetric(@RequestBody Metric addMetric){
         metricService.addValueToMetric(addMetric.getName(), addMetric.getValue());
    }

    @RequestMapping(value = "metric", method = RequestMethod.GET)
    public MetricStat getMetric(String metric, MetricStatType type){
        MetricStat stat = new MetricStat();
        stat.setName(metric);
        stat.setType(type);
        switch (type){
            case MAX:
                stat.setValue(metricService.getMax(metric));
                break;
            case MEAN:
                stat.setValue(metricService.getMean(metric));
                break;
            case MEDIAN:
                stat.setValue(metricService.getMedian(metric));
                break;
            case MIN:
                stat.setValue(metricService.getMin(metric));
        }
        return stat;
    }

}
