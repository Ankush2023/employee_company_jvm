package com.epms.company.model.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CompanyDetailsRequest {
    private String companyDesc;
    private String industry;
    private String type;
    private LocalDateTime foundedAt;
    private String founder;
    private String headquater;
    private String employeeCount;
    private String website;
    private String linkedinProfile;
    private String facebook_profile;
    private Long companyId;

}
