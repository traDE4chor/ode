package org.apache.ode.bpel.runtime.channels;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An auto-generated channel listener abstract class for the
 * {@link org.apache.ode.bpel.runtime.channels.DataNotification} channel type.
 * 
 * @see org.apache.ode.bpel.runtime.channels.DataNotification
 * @see org.apache.ode.bpel.runtime.channels.DataNotificationChannel
 */
public abstract class DataNotificationChannelListener extends
        org.apache.ode.jacob.ChannelListener<org.apache.ode.bpel.runtime.channels.DataNotificationChannel>
        implements org.apache.ode.bpel.runtime.channels.DataNotification {

    private static final Logger __log =
            LoggerFactory.getLogger(org.apache.ode.bpel.runtime.channels.DataNotification.class);

    protected Logger log() {
        return __log;
    }

    protected DataNotificationChannelListener(
            org.apache.ode.bpel.runtime.channels.DataNotificationChannel channel) {
        super(channel);
    }
}
