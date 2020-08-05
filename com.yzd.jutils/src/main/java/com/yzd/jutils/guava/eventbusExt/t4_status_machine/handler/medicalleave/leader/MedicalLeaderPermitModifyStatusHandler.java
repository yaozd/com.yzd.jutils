package com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.medicalleave.leader;

import com.yzd.jutils.guava.eventbusExt.t4_status_machine.event.LeavePermit;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.StatusHandler;

/**
 * Created by jetty on 18/1/9.
 */
public class MedicalLeaderPermitModifyStatusHandler extends StatusHandler{

    @Override
    protected void doHandler(LeavePermit leavePermit){
        System.out.println(String.format("user:%s--领导返回补充病假材料--leavePermit status:%s-%s",leavePermit.getUser(),leavePermit.getStatus().getStatus(),leavePermit.getStatus().getMemo()));
    }

}
