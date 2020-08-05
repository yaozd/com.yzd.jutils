package com.yzd.jutils.guava.eventbusExt.t4_status_machine;

import com.yzd.jutils.guava.eventbusExt.t4_status_machine.engine.StatusMachineEngine;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.event.Event;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.event.LeavePermit;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.event.LeavePermitType;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.event.Status;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.medicalleave.MedicalPermitFailStatusHandler;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.medicalleave.MedicalPermitSubmitStatusHandler;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.medicalleave.MedicalPermitSuccessStatusHandler;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.medicalleave.hr.MedicalHrAgreeStatusHandler;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.medicalleave.hr.MedicalHrDisAgreeStatusHandler;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.medicalleave.hr.MedicalHrPermitModifyStatusHandler;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.medicalleave.hr.MedicalHrPermitingStatusHandler;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.medicalleave.leader.MedicalLeaderAgreeStatusHandler;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.medicalleave.leader.MedicalLeaderDisAgreeStatusHandler;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.medicalleave.leader.MedicalLeaderPermitModifyStatusHandler;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.medicalleave.leader.MedicalLeaderPermitingStatusHandler;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.listener.LeavePermitEventListener;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.registry.StatusHandlerRegistry;

/**
 * @Author: yaozh
 * @Description:
 */
public class _MainTest {
    /**
     * 推荐参考byArvin
     * 更加详细的说明，请参考如下：*******
     * - [EventBus-实现java状态机](https://www.jianshu.com/p/8def04b34b3c) -推荐参考byArvin
     *     - [https://github.com/jettyrun/statusMachine](https://github.com/jettyrun/statusMachine)
     *     - [https://github.com/yaozd/statusMachine](https://github.com/yaozd/statusMachine)
     * @param args
     */
    public static void main(String[] args) {
        //注册病假的状态和对应状态的处理类StatusHandler。
        registryMedicalPermitStatusHandler();
        //状态机引擎接受事件处理类
        StatusMachineEngine.addListener(new LeavePermitEventListener());
        //生成假单
        LeavePermit leavePermit=new LeavePermit();
        //leavePermit.setLeavePermitType(LeavePermitType.ANNUAL_LEAVE);
        leavePermit.setLeavePermitType(LeavePermitType.MEDICAL_LEAVE);
        leavePermit.setStatus(Status.PERMIT_SUBMIT);
        leavePermit.setUser("jettyrun");
        //假单交给引擎去执行
        StatusMachineEngine.post(leavePermit);

        System.out.println("----- 分割线 代表假条需要领导审批了，领导给个通过意见,然后状态机接着走-------");
        leavePermit.setEvent(Event.AGREE);
        StatusMachineEngine.post(leavePermit);
        System.out.println("----- 分割线 代表假条需要ceo审批了，ceo给个通过意见,然后状态机接着走-------");
        leavePermit.setEvent(Event.AGREE);
        StatusMachineEngine.post(leavePermit);
        System.out.println("--->>>>>>>>>end<<<<<<<<-------");
    }

    /**
     * 注册事件处理
     */
    public static void registryMedicalPermitStatusHandler() {

        StatusHandlerRegistry.registryStatusHandler(LeavePermitType.MEDICAL_LEAVE, Status.PERMIT_SUBMIT, new MedicalPermitSubmitStatusHandler());

        StatusHandlerRegistry.registryStatusHandler(LeavePermitType.MEDICAL_LEAVE, Status.LEADER_PERMIT_AGREE, new MedicalLeaderAgreeStatusHandler());
        StatusHandlerRegistry.registryStatusHandler(LeavePermitType.MEDICAL_LEAVE, Status.LEADER_PERMIT_DISAGREE, new MedicalLeaderDisAgreeStatusHandler());
        StatusHandlerRegistry.registryStatusHandler(LeavePermitType.MEDICAL_LEAVE, Status.LEADER_PERMIT_MODIFY, new MedicalLeaderPermitModifyStatusHandler());
        StatusHandlerRegistry.registryStatusHandler(LeavePermitType.MEDICAL_LEAVE, Status.LEADER_PERMITING, new MedicalLeaderPermitingStatusHandler());

        StatusHandlerRegistry.registryStatusHandler(LeavePermitType.MEDICAL_LEAVE, Status.HR_PERMIT_AGREE, new MedicalHrAgreeStatusHandler());
        StatusHandlerRegistry.registryStatusHandler(LeavePermitType.MEDICAL_LEAVE, Status.HR_PERMIT_DISAGREE, new MedicalHrDisAgreeStatusHandler());
        StatusHandlerRegistry.registryStatusHandler(LeavePermitType.MEDICAL_LEAVE, Status.HR_PERMIT_MODIFY, new MedicalHrPermitModifyStatusHandler());
        StatusHandlerRegistry.registryStatusHandler(LeavePermitType.MEDICAL_LEAVE, Status.HR_PERMITING, new MedicalHrPermitingStatusHandler());

        StatusHandlerRegistry.registryStatusHandler(LeavePermitType.MEDICAL_LEAVE, Status.PERMIT_SUCCESS, new MedicalPermitSuccessStatusHandler());
        StatusHandlerRegistry.registryStatusHandler(LeavePermitType.MEDICAL_LEAVE, Status.PERMIT_FAIL, new MedicalPermitFailStatusHandler());
    }
}
