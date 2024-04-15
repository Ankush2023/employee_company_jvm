package com.epms.company.model.request;

import com.epms.company.utils.LeaveType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeLeaveMasterRequest {
    private Long employeeLeaveMasterId;
    private LeaveType leaveType;
    private Long leaveBalance;



}
