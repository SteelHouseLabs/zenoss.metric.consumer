package org.zenoss.app.consumer.metric.data;

import java.util.Collections;
import java.util.Map;

public class Metric {
    private String metric;
    private long timestamp;
    private double value;
    private Map<String, String> tags;

    public Metric() {
    }

    public Metric(String metric, long timestamp, double value) {
        this.metric = metric;
        this.timestamp = timestamp;
        this.value = value;
        this.tags = Collections.emptyMap();
    }

    public Metric(String metric, long timestamp, double value, Map<String, String> tags) {
        this.metric = metric;
        this.timestamp = timestamp;
        this.value = value;
        this.tags = tags;
    }

    public String getMetric() {
        return metric;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getValue() {
        return value;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Metric other = (Metric) o;

        if (timestamp != other.timestamp) {
            return false;
        }
        if (Double.compare(other.value, value) != 0) {
            return false;
        }
        if (this.metric != null ? !this.metric.equals(other.metric) : other.metric != null) {
            return false;
        }
        if (tags != null ? !tags.equals(other.tags) : other.tags != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = metric != null ? metric.hashCode() : 0;
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Metric{" +
                "metric='" + metric + '\'' +
                ", timestamp=" + timestamp +
                ", value=" + value +
                ", tags=" + tags +
                '}';
    }
}
