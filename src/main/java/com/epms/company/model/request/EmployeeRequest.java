package com.epms.company.model.request;

import com.epms.company.model.Company;
import com.epms.company.model.Employee;
import com.epms.company.utils.JobType;
import com.epms.company.utils.WorkMode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmployeeRequest {

    private Long employeeId;

    private String employeeName;

    private String employeeCode;

    private String jobRole;

    private MultipartFile profile;

    private Double experience;

    private JobType jobType;

    private WorkMode workMode;

    private String email;

    private String mobileNo;

    private String addressLine1;

    private String addressLine2;

    private String password;

    private Long companyId;

    private Long reportingManagerId;
}
