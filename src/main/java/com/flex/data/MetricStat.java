package com.flex.data;

/**
 * Created by jasonskipper on 5/8/17. This class is used to hold a stat which has a type, name and value
 */
public class MetricStat {
    private String name;
    private MetricStatType type;
    private Double value;

    /**
     * Default constructor to be used for JSON serialization/ deserialization by the
     * Spring System.  All objects we intend to use with JSON should have a defaul constructor
     */
    public MetricStat(){

    }

    /**
     * Constructor that takes in values
     * @param name
     * @param type
     * @param value
     */
    public MetricStat(String name, MetricStatType type, Double value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

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
