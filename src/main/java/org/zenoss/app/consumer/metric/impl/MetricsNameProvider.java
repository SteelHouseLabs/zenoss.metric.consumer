/*
 * ***************************************************************************
 *
 *  Copyright (C) Zenoss, Inc. 2013, all rights reserved.
 *
 *  This content is made available according to terms specified in
 *  License.zenoss under the directory where your Zenoss product is installed.
 *
 * ***************************************************************************
 */
package org.zenoss.app.consumer.metric.impl;

import com.yammer.metrics.core.MetricName;


class MetricsNameProvider {

    MetricName incomingMetricName() {
        return new MetricName(MetricsQueue.class, "totalIncoming");
    }

    MetricName outgoingMetricName() {
        return new MetricName(MetricsQueue.class, "totalOutgoing");
    }

    MetricName inFlightMetricName() {
        return new MetricName(MetricsQueue.class, "totalInFlight");
    }

    MetricName errorsMetricName() {
        return new MetricName(MetricsQueue.class, "totalErrors");
    }
}
