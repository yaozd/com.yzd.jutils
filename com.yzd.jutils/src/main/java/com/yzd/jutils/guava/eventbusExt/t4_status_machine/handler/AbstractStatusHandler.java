package com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler;


import com.yzd.jutils.guava.eventbusExt.t4_status_machine.event.LeavePermit;

/**
 * Created by jetty on 18/1/9.
 */
public interface AbstractStatusHandler {

    public void handle(LeavePermit leavePermit);

}
