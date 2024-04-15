package com.epms.company.controller;

import com.epms.company.model.request.EmployeeRequest;
import com.epms.company.model.response.EntityResponse;
import com.epms.company.service.IEmployeeService;
import com.sun.mail.iap.Response;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "api/v1/employee")
@Api(value = "employee controller ", description = "employee controller ", tags = {"employee controller"})
public class EmployeeController {
    @Autowired
    private IEmployeeService iEmployeeService;

    @PostMapping("/saveOrUpdateEmployee")
    public ResponseEntity<?> saveOrUpdateEmployee(@ModelAttribute EmployeeRequest employeeRequest){
        return new ResponseEntity<>(new EntityResponse(iEmployeeService.saveOrUpdateEmployee(employeeRequest),0), HttpStatus.OK);
    }
    @GetMapping("/getEmployeeByEmployeeId")
    public ResponseEntity<?> getEmployeeByEmployeeId(@RequestParam Long employeeId){
        return new ResponseEntity<>(new EntityResponse(iEmployeeService.getEmployeeByEmployeeId(employeeId),0), HttpStatus.OK);
    }

}
