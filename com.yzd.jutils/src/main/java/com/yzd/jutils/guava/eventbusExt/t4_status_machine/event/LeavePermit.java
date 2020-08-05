package com.yzd.jutils.guava.eventbusExt.t4_status_machine.event;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: yaozh
 * @Description:
 */
@Getter
@Setter
public class LeavePermit {
    private Status status;
    private LeavePermitType leavePermitType;
    private Event event;
    private String user;
}
