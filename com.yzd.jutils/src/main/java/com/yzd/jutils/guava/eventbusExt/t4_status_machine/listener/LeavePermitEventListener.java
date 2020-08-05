package com.yzd.jutils.guava.eventbusExt.t4_status_machine.listener;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.event.LeavePermit;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.handler.StatusHandler;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.registry.StatusHandlerRegistry;

/**
 * 请假事件监听
 *
 * @Author: yaozh
 * @Description:
 */
public class LeavePermitEventListener {
    //处理假单 注解代表可以接受到StatusMachineEngine发布的假单
    @Subscribe
    @AllowConcurrentEvents
    public void handle(LeavePermit leavePermit) {
        //获取到状态处理类，然后去处理 handler为StatusHandler的入口
        getStatusHandler(leavePermit).handle(leavePermit);
    }

    /**
     * 根据假单获取StatusHandler 状态处理对象
     *
     * @param leavePermit
     * @return
     */
    public static StatusHandler getStatusHandler(LeavePermit leavePermit) {
        return StatusHandlerRegistry
                .acquireStatusHandler(leavePermit.getLeavePermitType(), leavePermit.getStatus());
    }
}
