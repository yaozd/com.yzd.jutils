package com.yzd.jutils.guava.eventbusExt.t4_status_machine.machine;


import com.yzd.jutils.guava.eventbusExt.t4_status_machine.event.LeavePermitType;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.machine.impl.AnnualLeaveStatusMachine;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.machine.impl.MedicalLeaveStatusMachine;

/**
 * Created by jetty on 18/1/9.
 */
public class StatusMachineFactory {

    private StatusMachineFactory(){

    }

    /**
     * 根据状态获取状态机
     * @param leavePermitType
     * @return 对应请假类型的状态机
     */
    public static StatusMachine getStatusMachine(LeavePermitType leavePermitType){
        switch (leavePermitType){
            case MEDICAL_LEAVE:
                return new MedicalLeaveStatusMachine();
            case ANNUAL_LEAVE:
                return new AnnualLeaveStatusMachine();
            default:
                throw new RuntimeException("未知类型");
        }
    }

}
