package com.epms.company.service.serviceimpl;

import com.epms.company.exception.CompanyNotFoundException;
import com.epms.company.exception.EmailAlreadyExistsException;
import com.epms.company.model.Employee;
import com.epms.company.model.request.EmployeeRequest;
import com.epms.company.repository.CompanyRepository;
import com.epms.company.repository.EmployeeRepository;
import com.epms.company.service.IEmployeeService;
import com.epms.company.utility.EmailConfig;
import com.epms.company.utility.FileStrorageOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmailConfig emailConfig;
    @Autowired
    private FileStrorageOperation fileStrorageOperation;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public Object saveOrUpdateEmployee(EmployeeRequest employeeRequest) {
        return employeeRepository.findById(employeeRequest.getEmployeeId()).map(employee -> {
            List<Long> employeeids= Collections.singletonList(employeeRequest.getEmployeeId());
            employee.setAddressLine1(employeeRequest.getAddressLine1());
            employee.setAddressLine2(employeeRequest.getAddressLine2());
            if(employeeRepository.existsByEmailAndIsDeletedAndEmployeeIdNotIn(employeeRequest.getEmail(),false,employeeids)){
                throw new EmailAlreadyExistsException("Employee email already exists");
            }else {
                employee.setEmail(employeeRequest.getEmail());
            }
            if(employeeRepository.existsByEmployeeCodeAndIsDeletedAndEmployeeIdNotIn(employeeRequest.getEmployeeCode(),false,employeeids)){
                throw new EmailAlreadyExistsException("Employee code already exists");
            }else{
                employee.setEmployeeCode(employeeRequest.getEmployeeCode());
            }
            employee.setEmployeeName(employeeRequest.getEmployeeName());
            employee.setExperience(employeeRequest.getExperience());
            employee.setJobRole(employeeRequest.getJobRole());
            employee.setJobType(employeeRequest.getJobType());
            employee.setMobileNo(employeeRequest.getMobileNo());
            employee.setWorkMode(employeeRequest.getWorkMode());
            employee.setProfile(fileStrorageOperation.uploadFile(employeeRequest.getProfile()));
            employeeRepository.findById(employeeRequest.getReportingManagerId()).map(employee1 -> {
                employee.setEmployee(employee1);
                return "";
            });
           companyRepository.findById(employeeRequest.getCompanyId()).map(company ->{
               employee.setCompany(company);
               return "";
           });
           employeeRepository.save(employee);
           return "updated successfully";
        }).orElseGet(() -> {
            Employee employee=new Employee();
            employee.setAddressLine1(employeeRequest.getAddressLine1());
            employee.setAddressLine2(employeeRequest.getAddressLine2());
            if(employeeRepository.existsByEmailAndIsDeleted(employeeRequest.getEmail(),false)){
                throw new EmailAlreadyExistsException("Employee email already exists");
            }else {
                employee.setEmail(employeeRequest.getEmail());
            }
            if(employeeRepository.existsByEmployeeCodeAndIsDeleted(employeeRequest.getEmployeeCode(),false)){
               throw new EmailAlreadyExistsException("Employee code already exists");
            }else{
                employee.setEmployeeCode(employeeRequest.getEmployeeCode());
            }
            employee.setEmployeeName(employeeRequest.getEmployeeName());
            employee.setExperience(employeeRequest.getExperience());
            employee.setJobRole(employeeRequest.getJobRole());
            employee.setJobType(employeeRequest.getJobType());
            employee.setMobileNo(employeeRequest.getMobileNo());
            employee.setWorkMode(employeeRequest.getWorkMode());
            employee.setIsActive(true);
            employee.setIsDeleted(false);
            employee.setPassword(bCryptPasswordEncoder.encode(employeeRequest.getPassword()));
            employee.setProfile(fileStrorageOperation.uploadFile(employeeRequest.getProfile()));
            employeeRepository.findById(employeeRequest.getReportingManagerId()).map(employee1 -> {
                employee.setEmployee(employee1);
                return "";
            });
            companyRepository.findById(employeeRequest.getCompanyId()).map(company ->{
                employee.setCompany(company);
                return "";
            });
            employeeRepository.save(employee);
            emailConfig.sendEmail(employeeRequest.getEmail(),"new Account created","Account is created for Employee : "+employeeRequest.getEmployeeName());
            return "saved successfully";
        });

    }

    @Override
    public Object getEmployeeByEmployeeId(Long employeeId) {
        return employeeRepository.findById(employeeId).map(employee -> {
            if(employee.getProfile()!=null) {
                employee.setProfile(fileStrorageOperation.getFilePath(employee.getProfile()));
            }
            return employee;
        }).orElseThrow(() -> new CompanyNotFoundException("Employee Not found"));
    }
}
