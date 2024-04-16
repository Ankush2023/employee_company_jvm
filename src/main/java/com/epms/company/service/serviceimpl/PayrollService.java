package com.epms.company.service.serviceimpl;

import com.epms.company.exception.CompanyNotFoundException;
import com.epms.company.model.EmployeeLeaveMaster;
import com.epms.company.model.request.EmployeeLeaveMasterRequest;
import com.epms.company.model.response.Pagedto;
import com.epms.company.repository.CompanyRepository;
import com.epms.company.repository.EmployeeLeaveMasterRepository;
import com.epms.company.service.IPayrollService;
import com.epms.company.utils.LeaveType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PayrollService implements IPayrollService {
    @Autowired
    private EmployeeLeaveMasterRepository employeeLeaveMasterRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public Object saveOrUpdateEmployeeLeaveMaster(EmployeeLeaveMasterRequest employeeLeaveMasterRequest) {
        return employeeLeaveMasterRepository.findByEmployeeLeaveMasterIdAndLeaveType(employeeLeaveMasterRequest.getEmployeeLeaveMasterId(),employeeLeaveMasterRequest.getLeaveType()).map(employeeLeaveMaster -> {
            employeeLeaveMaster.setLeaveBalance(employeeLeaveMasterRequest.getLeaveBalance());
            employeeLeaveMaster.setLeaveType(employeeLeaveMasterRequest.getLeaveType());
            companyRepository.findById(employeeLeaveMasterRequest.getCompanyId()).map(company -> {
                employeeLeaveMaster.setCompany(company);
                return "";
            }).orElseThrow(() -> new CompanyNotFoundException("Company not found"));
            employeeLeaveMasterRepository.save(employeeLeaveMaster);
            return "Updated successfully";
        }).orElseGet(() -> {
            EmployeeLeaveMaster employeeLeaveMaster=new EmployeeLeaveMaster();
            employeeLeaveMaster.setLeaveType(employeeLeaveMasterRequest.getLeaveType());
            employeeLeaveMaster.setLeaveBalance(employeeLeaveMasterRequest.getLeaveBalance());
            employeeLeaveMaster.setIsDeleted(false);
            employeeLeaveMaster.setIsActive(true);
            companyRepository.findById(employeeLeaveMasterRequest.getCompanyId()).map(company -> {
                employeeLeaveMaster.setCompany(company);
                return "";
            }).orElseThrow(() -> new CompanyNotFoundException("Company not found"));
            employeeLeaveMasterRepository.save(employeeLeaveMaster);
            return "saved successfully";
        });
    }

    @Override
    public Object getAllEmployeeLeaveMaster(Pageable pageable) {
        Page<EmployeeLeaveMaster> employeeLeaveMaster= employeeLeaveMasterRepository.findAllByIsDeleted(false,pageable);
        return new Pagedto(employeeLeaveMaster.getTotalElements(),employeeLeaveMaster.getContent(),employeeLeaveMaster.getNumber(),employeeLeaveMaster.getTotalPages());
    }

    @Override
    public Object searchEmployeeLeaveMaster(Pageable pageable, LeaveType leaveType) {
        Page<EmployeeLeaveMaster> employeeLeaveMaster= Optional.ofNullable(leaveType).map(leaveType1 -> {
            return employeeLeaveMasterRepository.findAllByIsDeletedAndLeaveType(false,leaveType1,pageable);
        }).orElseGet(() -> {
            return employeeLeaveMasterRepository.findAllByIsDeleted(false, pageable);
        }
        );
        return new Pagedto(employeeLeaveMaster.getTotalElements(),employeeLeaveMaster.getContent(),employeeLeaveMaster.getNumber(),employeeLeaveMaster.getTotalPages());

    }
}
