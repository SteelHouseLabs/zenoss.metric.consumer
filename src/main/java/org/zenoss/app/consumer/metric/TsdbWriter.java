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
package org.zenoss.app.consumer.metric;

/**
 *
 * @author cschellenger
 */
public interface TsdbWriter extends Runnable {

    void cancel();
    boolean isRunning();
    
}
