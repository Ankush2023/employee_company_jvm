package com.epms.company.controller;

import com.epms.company.model.request.EmployeeLeaveMasterRequest;
import com.epms.company.model.response.EntityResponse;
import com.epms.company.service.IPayrollService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> getAllEmployeeLeaveMaster(){
        return new ResponseEntity<>(new EntityResponse(iPayrollService.getAllEmployeeLeaveMaster(),0), HttpStatus.OK);
    }
}
