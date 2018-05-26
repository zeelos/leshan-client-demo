package io.zeelos.leshan.client.demo;

public class DataPoint {

    private Long timestamp;
    private Double value;

    private Double min;
    private Double max;

    private DataPoint() {
    }

    public DataPoint(Double value, Long timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }

    public DataPoint(Double value, Long timestamp, Double max, Double min) {
        this.value = value;
        this.timestamp = timestamp;
        this.min = min;
        this.max = max;
    }

    public Double getValue() {
        return value;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataPoint)) return false;

        DataPoint dataPoint = (DataPoint) o;

        if (!timestamp.equals(dataPoint.timestamp)) return false;
        return value.equals(dataPoint.value);

    }

    @Override
    public int hashCode() {
        int result = timestamp.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " { value = " + value + " , timestamp=" + timestamp + " }";
    }
}
