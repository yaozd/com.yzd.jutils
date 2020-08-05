package com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.medicalleave.leader;

import com.yzd.jutils.guava.eventbusExt.t4_status_machine.event.LeavePermit;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.StatusHandler;

/**
 * Created by jetty on 18/1/9.
 */
public class MedicalLeaderPermitingStatusHandler extends StatusHandler{
    @Override
    protected void doHandler(LeavePermit leavePermit){
        System.out.println(String.format("user:%s--领导审批病假中--leavePermit status:%s-%s",leavePermit.getUser(),leavePermit.getStatus().getStatus(),leavePermit.getStatus().getMemo()));
    }
    @Override
    protected void after(LeavePermit leavePermit){

        if(leavePermit.getEvent()==null){
            //还未审批，状态机结束，等待审批意见
            System.out.println(String.format("user:%s--等待领导病假审批--leavePermit status:%s-%s",leavePermit.getUser(),leavePermit.getStatus().getStatus(),leavePermit.getStatus().getMemo()));
            return;
        }
        goNextStatusHandler(leavePermit);
    }
}
