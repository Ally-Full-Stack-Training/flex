package com.flex.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jasonskipper on 5/8/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MetricServiceTest {
    @Autowired
    private MetricService metricService;

    @Test
    public void testSingleMetric() throws Exception {
        metricService.clear();
        metricService.addMetric("TEST");
        metricService.addValueToMetric("TEST", 1.0);
        metricService.addValueToMetric("TEST", 2.0);
        metricService.addValueToMetric("TEST", 3.0);
        metricService.addValueToMetric("TEST", 4.0);
        metricService.addValueToMetric("TEST", 5.0);
        metricService.addValueToMetric("TEST", 5.0);
        metricService.addValueToMetric("TEST", 5.0);

        Assert.assertEquals("Unexpected MAX Value", 5, metricService.getMax("TEST"),0.001);
        Assert.assertEquals("Unexpected MIN Value", 1, metricService.getMin("TEST"),0.001);
        Assert.assertEquals("Unexpected MEAN Value", 3.57, metricService.getMean("TEST"),.1);
        Assert.assertEquals("Unexpected MEDIAN Value", 4, metricService.getMedian("TEST"),.1);

    }
    @Test
    public void testSingleMetricEvenNumDataset() throws Exception {
        metricService.clear();
        metricService.addMetric("TEST");
        metricService.addValueToMetric("TEST", 1.0);
        metricService.addValueToMetric("TEST", 2.0);
        metricService.addValueToMetric("TEST", 5.0);
        metricService.addValueToMetric("TEST", 5.0);
        Assert.assertEquals("Unexpected MEDIAN Value", 3.5, metricService.getMedian("TEST"),.1);

    }
    @Test
    public void testMultiMetric() throws Exception {
        metricService.clear();
        // first metric
        metricService.addMetric("TEST1");
        metricService.addValueToMetric("TEST1", 1.0);
        metricService.addValueToMetric("TEST1", 2.0);
        metricService.addValueToMetric("TEST1", 3.0);
        metricService.addValueToMetric("TEST1", 4.0);
        metricService.addValueToMetric("TEST1", 5.0);
        metricService.addValueToMetric("TEST1", 5.0);
        metricService.addValueToMetric("TEST1", 5.0);

        // second metric
        metricService.addMetric("TEST2");
        metricService.addValueToMetric("TEST2", 0.2);
        metricService.addValueToMetric("TEST2", 1.5);
        metricService.addValueToMetric("TEST2", 2.75);
        metricService.addValueToMetric("TEST2", 15.0);
        metricService.addValueToMetric("TEST2", 25.0);
        metricService.addValueToMetric("TEST2", 30.0);
        metricService.addValueToMetric("TEST2", 50.0);

        Assert.assertEquals("Unexpected MAX Value", 5, metricService.getMax("TEST1"),0.001);
        Assert.assertEquals("Unexpected MIN Value", 1, metricService.getMin("TEST1"),0.001);
        Assert.assertEquals("Unexpected MEAN Value", 3.57, metricService.getMean("TEST1"),.1);
        Assert.assertEquals("Unexpected MEDIAN Value", 4, metricService.getMedian("TEST1"),.1);

        Assert.assertEquals("Unexpected MAX Value", 50, metricService.getMax("TEST2"),0.001);
        Assert.assertEquals("Unexpected MIN Value", 0.2, metricService.getMin("TEST2"),0.001);
        Assert.assertEquals("Unexpected MEAN Value", 17.7785, metricService.getMean("TEST2"),.1);
        Assert.assertEquals("Unexpected MEDIAN Value", 15, metricService.getMedian("TEST2"), .001);
    }

    @Test
    public void testGetNonExistentMetric() throws Exception {
        metricService.addMetric("TEST2");
        Assert.assertNull("Stat should be null", metricService.getMax("NOTHERE"));
    }

}