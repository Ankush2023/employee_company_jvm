package com.epms.company.service;

import com.epms.company.model.request.EmployeeRequest;

public interface IEmployeeService {
    Object saveOrUpdateEmployee(EmployeeRequest employeeRequest);

    Object getEmployeeByEmployeeId(Long employeeId);
}
