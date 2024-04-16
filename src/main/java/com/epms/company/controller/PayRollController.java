package com.epms.company.controller;

import com.epms.company.model.request.EmployeeLeaveMasterRequest;
import com.epms.company.model.response.EntityResponse;
import com.epms.company.service.IPayrollService;
import com.epms.company.utils.LeaveType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payroll")
public class PayRollController {
    @Autowired
    private IPayrollService iPayrollService;
    @PostMapping("saveOrUpdateEmployeeLeaveMaster")
    public ResponseEntity<?> saveOrUpdateEmployeeLeaveMaster(@RequestBody EmployeeLeaveMasterRequest employeeLeaveMasterRequest){
      return new ResponseEntity<>(new EntityResponse(iPayrollService.saveOrUpdateEmployeeLeaveMaster(employeeLeaveMasterRequest),0), HttpStatus.OK);
    }
    @GetMapping("getAllEmployeeLeaveMaster")
    public ResponseEntity<?> getAllEmployeeLeaveMaster(@RequestParam(defaultValue = "0") Integer pageNo,
                                                       @RequestParam(defaultValue = "30") Integer pageSize){
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        return new ResponseEntity<>(new EntityResponse(iPayrollService.getAllEmployeeLeaveMaster(pageable),0), HttpStatus.OK);
    }
    @GetMapping("searchEmployeeLeaveMaster")
    public ResponseEntity<?> searchEmployeeLeaveMaster(@RequestParam LeaveType leaveType,
                                                       @RequestParam(defaultValue = "0") Integer pageNo,
                                                       @RequestParam(defaultValue = "30") Integer pageSize){
        Pageable pageable= PageRequest.of(pageNo,pageSize);
        return new ResponseEntity<>(new EntityResponse(iPayrollService.searchEmployeeLeaveMaster(pageable,leaveType),0), HttpStatus.OK);
    }
}
