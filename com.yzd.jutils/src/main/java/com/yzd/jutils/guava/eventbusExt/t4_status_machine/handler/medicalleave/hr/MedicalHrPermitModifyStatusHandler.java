package com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.medicalleave.hr;

import com.yzd.jutils.guava.eventbusExt.t4_status_machine.event.LeavePermit;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.StatusHandler;

/**
 * Created by jetty on 18/1/9.
 */
public class MedicalHrPermitModifyStatusHandler extends StatusHandler {
    @Override
    protected void doHandler(LeavePermit leavePermit) {
        System.out.println(String.format("user:%s--hr需要补充病假材料--leavePermit status:%s-%s", leavePermit.getUser(), leavePermit.getStatus().getStatus(), leavePermit.getStatus().getMemo()));
    }

}
