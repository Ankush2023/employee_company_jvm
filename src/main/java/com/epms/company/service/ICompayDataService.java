package com.epms.company.service;

import com.epms.company.model.request.CompanyDetailsRequest;
import com.epms.company.model.request.CompanyRequest;

public interface ICompayDataService {
    Object saveOrUpdateCompany(CompanyRequest companyRequest);

    Object getCompanyByCompanyId(Long companyId);

    Object deleteCompanyByCompanyId(Long companyId);

    Object changeStatusByCompanyId(Long companyId);

    Object saveOrUpdateCompanyDetails(CompanyDetailsRequest companyDetailsRequest);
}
