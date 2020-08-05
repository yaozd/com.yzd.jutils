package com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.medicalleave;

import com.yzd.jutils.guava.eventbusExt.t4_status_machine.event.LeavePermit;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.StatusHandler;

/**
 * Created by jetty on 18/1/9.
 */
public class MedicalPermitFailStatusHandler extends StatusHandler {
    @Override
    protected void doHandler(LeavePermit leavePermit) {
        System.out.println(String.format("user:%s--病假审批失败--leavePermit status:%s-%s", leavePermit.getUser(), leavePermit.getStatus().getStatus(), leavePermit.getStatus().getMemo()));
    }

    @Override
    protected void after(LeavePermit leavePermit) {
    //最后的阶段-事件处理已经完成-结束
    }
}
