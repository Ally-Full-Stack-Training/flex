package com.flex.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by jasonskipper on 5/9/17.
 */
@Service("native")
public class NativeMetricService implements MetricServiceInterface {

    private HashMap<String, NativeMetricData> metricCatalogue = new HashMap();

    /**
     * Creates the metric if it does not already exist.  If it exists already then
     * no operation is performed.
     *
     * Worst Case Time Complexity O(1)
     * @param key
     */
    @Override
    public void addMetric(String key) {
        metricCatalogue.computeIfAbsent(key, k -> new NativeMetricData());
    }

    /**
     * Adds the given value to the specified metric (referenced by key).  The
     * list is sorted upon each insertion.
     *
     * Worst Case Time Complexity: O(n*log(n))
     * @param key
     * @param value
     */
    @Override
    public void addValueToMetric(String key, Double value) {
        metricCatalogue.computeIfPresent(key, (k, v) -> {
            v.addValue(value);
            return v;
        });
    }

    /**
     * Computes the mean value for the specified metric.  The sum is maintained on insertion so
     * computing the mean is always a constant step.
     *
     * Worst Case Time Complexity: O(1)
     * @param key
     * @return mean Or Null if metric does not exist/empty
     */
    @Override
    public Double getMean(String key) {
        NativeMetricData data = metricCatalogue.get(key);
        if (data != null) {
            return data.getMean();
        } else {
            return null;
        }
    }

    /**
     * Computes the Median value (as defined by the middle metric, or if set has even number, it will be the
     * average of the two middle values) for the specified metric.  Since our data is sorted, this is always a constant.
     *
     * Worst Case Time Complexity: O(1)
     * @param key
     * @return median Or Null if metric does not exist/empty
     */
    @Override
    public Double getMedian(String key) {
        NativeMetricData data = metricCatalogue.get(key);
        if (data != null) {
            return data.getMedian();
        } else {
            return null;
        }
    }

    /**
     * Computes the min value for the specified metric.  Since our data is sorted, this is always a constant.
     * @param key
     * @return min Or Null if metric does not exist/empty
     */
    @Override
    public Double getMin(String key) {
        NativeMetricData data = metricCatalogue.get(key);
        if (data != null) {
            return data.getMin();
        } else {
            return null;
        }
    }

    /**
     * Computes the Max value for the specified metric.  Since our data is sorted, this is always a constant.
     * @param key
     * @return Max Or Null if metric does not exist/empty
     */
    @Override
    public Double getMax(String key) {
        NativeMetricData data = metricCatalogue.get(key);
        if (data != null) {
            return data.getMax();
        } else {
            return null;
        }
    }

    @Override
    public boolean exists(String key) {
        return metricCatalogue.containsKey(key);
    }

    /**
     * Clears all data from this service.
     */
    @Override
    public void clear() {
        metricCatalogue.clear();
    }

    /**
     * Internal class for tracking Metric stats
     */
    class NativeMetricData {

        private double sum;
        private CopyOnWriteArrayList<Double> values;

        public NativeMetricData() {
            values = new CopyOnWriteArrayList();
        }

        /**
         * Performs sort on each insertion, also tracks current sum
         * Worst Case Time Complexity: O(n*log(n))
         */
        protected void addValue(Double val) {
            sum = sum + val;
            values.add(val);

            // now sort
            Collections.sort(values);
        }

        /**
         * Get min value. Operates in O(1)
         *
         * @return
         */
        protected Double getMin() {
            return values.get(0);
        }


        /**
         * Get max value. Operates in O(1)
         *
         * @return
         */
        protected Double getMax() {
            return values.get(values.size() - 1);
        }


        /**
         * Get Mean value. Operates in O(1)
         *
         * @return
         */
        protected Double getMean() {
            return sum / values.size();
        }

        /**
         * Get Media value. Operates in O(1)
         *
         * @return
         */
        protected Double getMedian() {
            Double median = null;

            if (values.size() > 0 && values.size() % 2 == 0) {
                int idx = values.size() / 2;
                median = (values.get(idx - 1) + values.get(idx)) / 2;
            } else if (values.size() > 0) {
                int idx = values.size() / 2;
                median = values.get(idx);
            }
            return median;
        }
    }
}
