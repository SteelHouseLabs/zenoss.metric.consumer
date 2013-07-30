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
package org.zenoss.app.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.zenoss.app.consumer.metric.MetricServiceConfiguration;
import org.zenoss.lib.tsdb.OpenTsdbClientPool;

/**
 *
 * @author cschellenger
 */
@Configuration
public class SpringConfig {
    
    @Autowired
    private ConsumerAppConfiguration consumerAppConfiguration;
    
    @Bean
    @Qualifier("zapp::executor::metrics")
    public ExecutorService metricsExecutorService() {
        return Executors.newFixedThreadPool(20);
    }
    
    @Bean
    public MetricServiceConfiguration metricsServiceConfiguration() {
        return consumerAppConfiguration.getMetricServiceConfiguration();
    }
    
    @Bean
    public OpenTsdbClientPool openTsdbClientPool() {
        return new OpenTsdbClientPool(metricsServiceConfiguration().getOpenTsdbClientPoolConfiguration());
    }
}
