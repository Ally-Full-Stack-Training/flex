package com.flex.controller;

import com.flex.data.Metric;
import com.flex.data.MetricStat;
import com.flex.data.MetricStatType;
import com.flex.service.MetricServiceInterface;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jasonskipper on 5/8/17.
 */
@RestController
@RequestMapping(path = "/flex/")
public class MetricController {

    @Autowired
    private MetricServiceInterface metricService;


    @ApiOperation(value = "Create a Metric aka Category", notes = "This operation completes with time complexity of O(1)")
    @RequestMapping(value = "metric", method = RequestMethod.POST)
    public void create(@RequestBody String name){
        metricService.addMetric(name);
    }

    @ApiOperation(value = "Add a value to a Metric", notes = "This operation completes with time complexity of O(1)")
    @RequestMapping(value = "metric/value", method = RequestMethod.POST)
    public void addValueToMetric(@RequestBody Metric addMetric){
         metricService.addValueToMetric(addMetric.getName(), addMetric.getValue());
    }

    @ApiOperation(value = "Get Metric Stats", notes = "This operation completes with time complexity of O(1)")
    @RequestMapping(value = "metric", method = RequestMethod.GET)
    public List<MetricStat> getMetric(String metric){
        return Arrays.asList(
                new MetricStat(metric,MetricStatType.MAX, metricService.getMax(metric)),
                new MetricStat(metric,MetricStatType.MIN, metricService.getMin(metric)),
                new MetricStat(metric,MetricStatType.MEAN, metricService.getMean(metric)),
                new MetricStat(metric,MetricStatType.MEDIAN, metricService.getMedian(metric)));
    }

}
