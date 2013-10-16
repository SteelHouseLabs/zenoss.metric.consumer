package org.zenoss.app.metric.zapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.zenoss.metrics.reporter.HttpPoster.METRIC_API;

public class MetricReporterConfig {

    public static final String ZENOSS_ZAPP_REPORTER = "zenoss-zapp-reporter";
    public static final int FREQUENCY = 30;
    public static final int SHUTDOWN_WAIT = 5;
    public static final String ZEN_INF = "ZEN_INF";

    static final String DEFAULT_PASSWORD = "zenoss";
    static final String DEFAULT_USER = "admin";

    static final String DEFAULT_MARKER = new Object().toString();

    /**
     * How often to report metrics in seconds
     */
    @Min(1)
    @JsonProperty
    private int reportFrequencySeconds = FREQUENCY;

    /**
     * How long to wait for shutdown of the reporter
     */
    @Min(1)
    @JsonProperty
    private int shutdownWaitSeconds = SHUTDOWN_WAIT;

    /**
     * report JVM metrics
     */
    @JsonProperty
    private boolean reportJvmMetrics = true;

    /**
     * Name for the metricreporter
     */
    @JsonProperty
    private String reporterName = ZENOSS_ZAPP_REPORTER;

    /**
     * Path to post metrics
     */
    @JsonProperty
    private String apiPath = METRIC_API;

    /**
     * Prefix added to reported metric names
     */
    @JsonProperty
    private String metricPrefix = ZEN_INF;

    /**
     * Host where metrics will be reported
     */
    @JsonProperty
    private String host = DEFAULT_MARKER;

    /**
     * Protocol for posting data, http or https
     */
    @JsonProperty
    private String protocol = DEFAULT_MARKER;

    /**
     * Port
     */
    @JsonProperty
    private Integer port = -1000;

    /**
     * User for posting metrics
     */
    @JsonProperty
    private String username = DEFAULT_USER;

    /**
     * Password for posting metrics
     */
    @JsonProperty
    private String password = DEFAULT_PASSWORD;


    public MetricReporterConfig() {
        super();
    }

    public MetricReporterConfig(int reportFrequencySeconds, String reporterName, String apiPath, String metricPrefix,
                                int shutdownWaitSeconds, boolean reportJvmMetrics, String host, String protocol,
                                int port, String username, String password) {
        this.reportFrequencySeconds = reportFrequencySeconds;
        this.reporterName = reporterName;
        this.apiPath = apiPath;
        this.metricPrefix = metricPrefix;
        this.shutdownWaitSeconds = shutdownWaitSeconds;
        this.reportJvmMetrics = reportJvmMetrics;
        this.port = port;
        this.host = host;
        this.protocol = protocol;
        this.username = username;
        this.password = password;
    }

    /**
     * How often to report metrics in seconds
     *
     * @return int
     */
    public int getReportFrequencySeconds() {
        return reportFrequencySeconds;
    }

    /**
     * How long to wait for outstanding report posting before shutting down.
     *
     * @return int
     */
    public int getShutdownWaitSeconds() {
        return shutdownWaitSeconds;
    }

    /**
     * Name for the metricreporter
     *
     * @return reporterName
     */
    public String getReporterName() {
        return reporterName;
    }

    /**
     * should JVM metrics be reported
     *
     * @return boolean
     */
    public boolean getReportJvmMetrics() {
        return reportJvmMetrics;
    }

    /**
     * Path to post metrics
     *
     * @return apiPath
     */
    public String getApiPath() {
        return apiPath;
    }

    /**
     * Prefix added to reported metric names
     *
     * @return metricPrefix
     */
    public String getMetricPrefix() {
        return metricPrefix;
    }


    /**
     * Port for posting data
     *
     * @return port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * Protocol for posting data, http or https
     *
     * @return protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Host where metrics will be reported
     *
     * @return host
     */
    public String getHost() {
        return host;
    }

    /**
     * Username for posting metrics
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Password for posting metrics
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    public static final class Builder {

        public Builder setReportFrequencySeconds(int reportFrequencySeconds) {
            checkArgument(reportFrequencySeconds > 0);
            this.reportFrequencySeconds = reportFrequencySeconds;
            return this;
        }

        public Builder setShutdownWaitSeconds(int shutdownWaitSeconds) {
            checkArgument(shutdownWaitSeconds > 0);
            this.shutdownWaitSeconds = shutdownWaitSeconds;
            return this;
        }

        public Builder setReporterName(String reporterName) {
            checkArgument(Strings.nullToEmpty(reporterName).trim().length() > 0);
            this.reporterName = reporterName;
            return this;
        }

        public Builder setApiPath(String apiPath) {
            checkNotNull(apiPath);
            this.apiPath = apiPath;
            return this;
        }

        public Builder setMetricPrefix(String metricPrefix) {
            this.metricPrefix = metricPrefix;
            return this;
        }

        public Builder setReportJvmMetrics(boolean reportJvmMetrics) {
            this.reportJvmMetrics = reportJvmMetrics;
            return this;
        }

        public Builder setHost(String host) {
            this.host = host;
            return this;
        }

        public Builder setProtocol(String protocol) {
            String proto = Strings.nullToEmpty(protocol).trim().toLowerCase();
            checkArgument(proto == "http" || proto == "https");
            this.protocol = protocol;
            return this;
        }

        public Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public Builder setUsername(String user) {
            this.username = user;
            return this;
        }

        public Builder setPassword(String password) {
            this.password= password;
            return this;
        }


        public MetricReporterConfig build() {
            return new MetricReporterConfig(reportFrequencySeconds, reporterName, apiPath, metricPrefix,
                    shutdownWaitSeconds, reportJvmMetrics, host, protocol, port, username, password);
        }

        private String reporterName = ZENOSS_ZAPP_REPORTER;
        private String apiPath = METRIC_API;
        private String metricPrefix = ZEN_INF;
        private String host = DEFAULT_MARKER;
        private String protocol = DEFAULT_MARKER;
        private String username = DEFAULT_USER;
        private String password = DEFAULT_PASSWORD;
        private Integer port = -1000;
        private int reportFrequencySeconds = FREQUENCY;
        private int shutdownWaitSeconds = SHUTDOWN_WAIT;
        private boolean reportJvmMetrics = true;


    }
}
