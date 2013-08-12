package org.zenoss.app.consumer.metric.data;

import java.util.Arrays;

public class Message {

    private Control control;

    private Metric[] metrics;

    public Message() {
        this.metrics= new Metric[]{};
    }

    public Message( Control control, Metric[] metrics) {
        this.control = control;
        this.metrics = metrics;
    }

    public Control getControl() {
        return control;
    }

    public Metric[] getMetrics() {
        return metrics;
    }

    public void setControl(Control control) {
        this.control = control;
    }

    public void setMetrics(Metric[] metrics) {
        this.metrics = metrics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Message other = (Message) o;

        if (control != null ? !control.equals(other.control) : other.control != null) {
            return false;
        }
        if (!Arrays.equals(metrics, other.metrics)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = control != null ? control.hashCode() : 0;
        result = 31 * result + (metrics != null ? Arrays.hashCode(metrics) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "control=" + control +
                ", metrics=" + Arrays.toString(metrics) +
                '}';
    }
}
