package com.epms.company.service.serviceimpl;

import com.epms.company.model.EmployeeLeaveMaster;
import com.epms.company.model.request.EmployeeLeaveMasterRequest;
import com.epms.company.repository.EmployeeLeaveMasterRepository;
import com.epms.company.service.IPayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayrollService implements IPayrollService {
    @Autowired
    private EmployeeLeaveMasterRepository employeeLeaveMasterRepository;
    @Override
    public Object saveOrUpdateEmployeeLeaveMaster(EmployeeLeaveMasterRequest employeeLeaveMasterRequest) {
        return employeeLeaveMasterRepository.findById(employeeLeaveMasterRequest.getEmployeeLeaveMasterId()).map(employeeLeaveMaster -> {
            employeeLeaveMaster.setLeaveBalance(employeeLeaveMasterRequest.getLeaveBalance());
            employeeLeaveMaster.setLeaveType(employeeLeaveMasterRequest.getLeaveType());
            employeeLeaveMasterRepository.save(employeeLeaveMaster);
            return "Updated successfully";
        }).orElseGet(() -> {
            EmployeeLeaveMaster employeeLeaveMaster=new EmployeeLeaveMaster();
            employeeLeaveMaster.setLeaveType(employeeLeaveMasterRequest.getLeaveType());
            employeeLeaveMaster.setLeaveBalance(employeeLeaveMasterRequest.getLeaveBalance());
            employeeLeaveMaster.setIsDeleted(false);
            employeeLeaveMaster.setIsActive(true);
            employeeLeaveMasterRepository.save(employeeLeaveMaster);
            return "saved successfully";
        });
    }

    @Override
    public Object getAllEmployeeLeaveMaster() {
        return employeeLeaveMasterRepository.findAllByIsDeleted(false);
    }
}
