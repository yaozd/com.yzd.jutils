package com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler;

import com.yzd.jutils.guava.eventbusExt.t4_status_machine.engine.StatusMachineEngine;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.event.LeavePermit;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.event.Status;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.machine.StatusMachineFactory;

/**
 * Created by jetty on 18/1/9.
 */
public abstract class StatusHandler implements AbstractStatusHandler {

    protected void before(LeavePermit leavePermit) {

    }


    @Override
    public void handle(LeavePermit leavePermit) {
        before(leavePermit);
        doHandler(leavePermit);
        after(leavePermit);
    }

    /**
     * do handler
     * @param leavePermit
     */
    protected abstract void doHandler(LeavePermit leavePermit);


    protected void after(LeavePermit leavePermit) {
        goNextStatusHandler(leavePermit);
    }

    protected void goNextStatusHandler(LeavePermit leavePermit) {
        //获取下一个状态
        Status nextStatus = StatusMachineFactory.getStatusMachine(leavePermit.getLeavePermitType())
                .getNextStatus(leavePermit.getStatus(), leavePermit.getEvent());
        leavePermit.setStatus(nextStatus);
        //状态机引擎去处理该假单
        StatusMachineEngine.post(leavePermit);
    }
}
