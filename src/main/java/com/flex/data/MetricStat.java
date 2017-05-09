package com.flex.data;

/**
 * Created by jasonskipper on 5/8/17.
 */
public class MetricStat {
    private String name;
    private MetricStatType type;
    private Double value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MetricStatType getType() {
        return type;
    }

    public void setType(MetricStatType type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
