/*
 * ****************************************************************************
 *
 *  Copyright (C) Zenoss, Inc. 2013, all rights reserved.
 *
 *  This content is made available according to terms specified in
 *  License.zenoss under the directory where your Zenoss product is installed.
 *
 * ***************************************************************************
 */
package org.zenoss.app.consumer.metric.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;
import com.yammer.metrics.Metrics;
import com.yammer.metrics.reporting.AbstractPollingReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.zenoss.app.consumer.metric.MetricServiceConfiguration;
import org.zenoss.app.consumer.metric.data.Metric;
import org.zenoss.dropwizardspring.annotations.Managed;

/**
 * Report internal metrics to TSD.
 */
@Managed
class MetricsReporter extends AbstractPollingReporter implements com.yammer.dropwizard.lifecycle.Managed {
    
    @Autowired
    MetricsReporter(MetricServiceConfiguration config, MetricsQueue metricsQueue) {
        super(Metrics.defaultRegistry(), "ConsumerTsdbReporter");
        this.metricsQueue = metricsQueue;
        this.consumerName = config.getConsumerName();
        this.frequency = config.getSelfReportFrequency();
    }
    
    @Override
    public void run() {
        long timestamp = System.currentTimeMillis() / 1000;
        Metric incoming = buildMetric("Incoming", timestamp, metricsQueue.getOneMinuteIncoming());
        Metric outgoing = buildMetric("Outgoing", timestamp, metricsQueue.getOneMinuteOutgoing());
        Metric inFlight = buildMetric("InFlight", timestamp, metricsQueue.getTotalInFlight());
        metricsQueue.addAll(Lists.newArrayList(incoming, outgoing, inFlight));
    }
    
    Metric buildMetric(String name, long timestamp, double val) {
        Metric metric = new Metric();
        metric.setMetric(name);
        metric.setTimestamp(timestamp);
        metric.setValue(val);
         
        Map<String, String> tags = new HashMap<>();
        tags.put("name", consumerName);
        
        metric.setTags(tags);
        return metric;
    }

    @Override
    public void start() {
        if (frequency > 0) {
            log.info("Starting internal metrics reporter");
            start(frequency, TimeUnit.MILLISECONDS);
        }
        else {
            log.info("Internal metrics reporter disabled");
        }
    }

    @Override
    public void stop() {
        shutdown();
    }
    
    private static final Logger log = LoggerFactory.getLogger(MetricsReporter.class);
    
    private final MetricsQueue metricsQueue;
    private final String consumerName;
    private final int frequency;
}
