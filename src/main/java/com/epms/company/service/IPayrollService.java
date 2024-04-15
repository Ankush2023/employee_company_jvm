package com.epms.company.service;

import com.epms.company.model.request.EmployeeLeaveMasterRequest;

public interface IPayrollService {

    Object saveOrUpdateEmployeeLeaveMaster(EmployeeLeaveMasterRequest employeeLeaveMasterRequest);

    Object getAllEmployeeLeaveMaster();
}
