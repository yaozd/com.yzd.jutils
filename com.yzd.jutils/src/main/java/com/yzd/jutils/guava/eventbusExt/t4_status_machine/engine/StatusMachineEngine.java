package com.yzd.jutils.guava.eventbusExt.t4_status_machine.engine;

import com.google.common.eventbus.EventBus;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.event.LeavePermit;
import com.yzd.jutils.guava.eventbusExt.t4_status_machine.listener.LeavePermitEventListener;

/**
 * Created by jetty on 18/1/9.
 */
public class StatusMachineEngine {

    private static EventBus eventBus;

    static {
        eventBus = new EventBus();
    }

    /**
     * 发布一条假单
     *
     * @param leavePermit
     */
    public static void post(LeavePermit leavePermit) {
        eventBus.post(leavePermit);
    }

    /**
     * 假单处理类
     *
     * @param
     */
    public static void addListener(LeavePermitEventListener eventListener) {
        eventBus.register(eventListener);
    }
}


