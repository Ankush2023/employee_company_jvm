package com.epms.company.controller;

import com.epms.company.model.request.CompanyDetailsRequest;
import com.epms.company.model.request.CompanyRequest;
import com.epms.company.model.response.EntityResponse;
import com.epms.company.service.ICompayDataService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "api/v1/company")
@Api(value = "company controller ", description = "company controller ", tags = {"company controller"})
public class CompanyController {

    @Autowired
    private ICompayDataService iCompayDataService;

    @PostMapping("/saveOrUpdateCompany")
    public ResponseEntity<?> saveOrUpdateCompany(@ModelAttribute CompanyRequest companyRequest) {
        return new ResponseEntity<>(new EntityResponse(iCompayDataService.saveOrUpdateCompany(companyRequest), 0), HttpStatus.OK);
    }

    @GetMapping("/getCompanyByCompanyId")
    public ResponseEntity<?> getCompanyByCompanyId(@RequestParam Long companyId){
        return new ResponseEntity<>(new EntityResponse(iCompayDataService.getCompanyByCompanyId(companyId),0),HttpStatus.OK);
    }

    @DeleteMapping("/deleteCompanyByCompanyId")
    public ResponseEntity<?> deleteCompanyByCompanyId(@RequestParam Long companyId){
        return new ResponseEntity<>(new EntityResponse(iCompayDataService.deleteCompanyByCompanyId(companyId),0),HttpStatus.OK);
    }

    @PutMapping("/changeStatusByCompanyId")
    public ResponseEntity<?> changeStatusByCompanyId(@RequestParam Long companyId){
        return new ResponseEntity<>(new EntityResponse(iCompayDataService.changeStatusByCompanyId(companyId),0),HttpStatus.OK);
    }
    @PostMapping("/saveOrUpdateCompanyDetails")
    public ResponseEntity<?> saveOrUpdateCompanyDetails(@ModelAttribute CompanyDetailsRequest companyDetailsRequest) {
        return new ResponseEntity<>(new EntityResponse(iCompayDataService.saveOrUpdateCompanyDetails(companyDetailsRequest), 0), HttpStatus.OK);
    }


}
