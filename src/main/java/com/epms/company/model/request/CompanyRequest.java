package com.epms.company.model.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CompanyRequest {
    private Long companyId;
    private String companyName;
    private String email;
    private String mobileNo;
    private String addressLine1;
    private String addressLine2;
    private String password;
    private MultipartFile multipartFile;
}
