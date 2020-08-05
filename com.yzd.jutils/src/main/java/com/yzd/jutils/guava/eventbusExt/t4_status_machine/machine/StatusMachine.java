package com.yzd.jutils.guava.eventbusExt.t4_status_machine.machine;

import com.yzd.jutils.guava.eventbusExt.t4_status_machine.event.Event;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.event.Status;

/**
 * Created by jetty on 18/1/8.
 */
public interface StatusMachine {

    public Status getNextStatus(Status status, Event event);

}
