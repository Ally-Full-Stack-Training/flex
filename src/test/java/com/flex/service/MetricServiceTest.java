package com.flex.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by jasonskipper on 5/8/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MetricServiceTest {

    @Resource(name="test")
    private MetricServiceInterface metricService;


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
        Assert.assertNull("Max should be null", metricService.getMax("NOTHERE"));
        Assert.assertNull("Mine should be null", metricService.getMin("NOTHERE"));
        Assert.assertNull("Mean should be null", metricService.getMean("NOTHERE"));
        Assert.assertNull("Median should be null", metricService.getMedian("NOTHERE"));
    }

    @Test
    public void testEmpty() throws Exception {
        metricService.clear();
        Assert.assertNull("Max should be null", metricService.getMax("NOTHERE"));
        Assert.assertNull("Mine should be null", metricService.getMin("NOTHERE"));
        Assert.assertNull("Mean should be null", metricService.getMean("NOTHERE"));
        Assert.assertNull("Median should be null", metricService.getMedian("NOTHERE"));

    }

    @Test
    public void testOne() throws Exception {
        metricService.clear();
        metricService.addMetric("one");
        metricService.addValueToMetric("one", 1d);
        Assert.assertEquals("Max error with single value", 1, metricService.getMax("one"), .001);
        Assert.assertEquals("Min error with single value", 1, metricService.getMin("one"), .001);
        Assert.assertEquals("Median error with single value", 1, metricService.getMedian("one"), .001);
        Assert.assertEquals("Mean error with single value", 1, metricService.getMean("one"), .001);
    }

    @Test
    public void testTwo() throws Exception {
        metricService.clear();
        metricService.addMetric("two");
        metricService.addValueToMetric("two", 1d);
        metricService.addValueToMetric("two", 2d);
        Assert.assertEquals("Max error with single value", 2, metricService.getMax("two"), .001);
        Assert.assertEquals("Min error with single value", 1, metricService.getMin("two"), .001);
        Assert.assertEquals("Median error with single value", 1.5, metricService.getMedian("two"), .001);
        Assert.assertEquals("Mean error with single value", 1.5, metricService.getMean("two"), .001);
    }
}