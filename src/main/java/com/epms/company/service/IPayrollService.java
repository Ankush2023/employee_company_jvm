package com.epms.company.service;

import com.epms.company.model.request.EmployeeLeaveMasterRequest;
import com.epms.company.utils.LeaveType;
import org.springframework.data.domain.Pageable;

public interface IPayrollService {

    Object saveOrUpdateEmployeeLeaveMaster(EmployeeLeaveMasterRequest employeeLeaveMasterRequest);

    Object getAllEmployeeLeaveMaster(Pageable pageable);

    Object searchEmployeeLeaveMaster(Pageable pageable,LeaveType leaveType);
}
