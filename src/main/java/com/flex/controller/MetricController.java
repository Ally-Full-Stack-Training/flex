package com.flex.controller;

import com.flex.data.Metric;
import com.flex.data.MetricStat;
import com.flex.data.MetricStatType;
import com.flex.exception.MetricNotFound;
import com.flex.service.MetricServiceInterface;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jasonskipper on 5/8/17.
 */
@RestController
@RequestMapping(path = "/flex/")
public class MetricController {

    @Autowired
    @Resource(name="native")
    private MetricServiceInterface metricService;


    @ApiOperation(value = "Create a Metric aka Category", notes = "This operation completes with time complexity of O(1)")
    @RequestMapping(value = "metric", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody String name){
        if( !metricService.exists(name)) {
            metricService.addMetric(name);
            return new ResponseEntity(HttpStatus.CREATED);
        }else {
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Add a value to a Metric", notes = "This operation completes with time complexity of O(n*log(n))")
    @RequestMapping(value = "metric/value", method = RequestMethod.POST)
    public ResponseEntity addValueToMetric(@RequestBody Metric addMetric){
        if( metricService.exists(addMetric.getName())) {
            metricService.addValueToMetric(addMetric.getName(), addMetric.getValue());
            return new ResponseEntity(HttpStatus.CREATED);
        }else {
            throw new MetricNotFound("No metric found by name:"+addMetric.getName());
        }

    }

    @ApiOperation(value = "Get Metric Stats", notes = "This operation completes with time complexity of O(1)")
    @RequestMapping(value = "metric", method = RequestMethod.GET)
    public List<MetricStat> getMetric(String metric){
        if( !metricService.exists(metric)) {
            throw new MetricNotFound("No metric found by name:"+metric);
        }else{
            return Arrays.asList(
                    new MetricStat(metric, MetricStatType.MAX, metricService.getMax(metric)),
                    new MetricStat(metric, MetricStatType.MIN, metricService.getMin(metric)),
                    new MetricStat(metric, MetricStatType.MEAN, metricService.getMean(metric)),
                    new MetricStat(metric, MetricStatType.MEDIAN, metricService.getMedian(metric)));        }
    }


}
