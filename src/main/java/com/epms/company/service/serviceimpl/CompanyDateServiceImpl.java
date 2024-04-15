package com.epms.company.service.serviceimpl;

import com.epms.company.exception.CompanyNotFoundException;
import com.epms.company.exception.EmailAlreadyExistsException;
import com.epms.company.model.Company;
import com.epms.company.model.CompanyDetails;
import com.epms.company.model.request.CompanyDetailsRequest;
import com.epms.company.model.request.CompanyRequest;
import com.epms.company.repository.ComapanyDetailsRepository;
import com.epms.company.repository.CompanyRepository;
import com.epms.company.service.ICompayDataService;
import com.epms.company.utility.EmailConfig;
import com.epms.company.utility.FileStrorageOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CompanyDateServiceImpl implements ICompayDataService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private FileStrorageOperation fileStrorageOperation;
    @Autowired
    private EmailConfig emailConfig;
    @Autowired
    private ComapanyDetailsRepository comapanyDetailsRepository;
    @Override
    public Object saveOrUpdateCompany(CompanyRequest companyRequest) {
        return companyRepository.findById(companyRequest.getCompanyId()).map(company -> {
            List<Long> companyIds = Collections.singletonList(companyRequest.getCompanyId());
            if (companyRepository.existsByEmailIgnoreCaseAndIsDeletedAndCompanyIdNotIn(companyRequest.getEmail(), false, companyIds)) {
                throw new EmailAlreadyExistsException("Email Already Exists");
            }
            if (companyRepository.existsByMobileNoIgnoreCaseAndIsDeletedAndCompanyIdNotIn(companyRequest.getMobileNo(), false, companyIds)) {
                throw new EmailAlreadyExistsException("Mobile No Already Exists");
            }
            company.setCompanyName(companyRequest.getCompanyName());
            company.setAddressLine1(companyRequest.getAddressLine1());
            company.setAddressLine2(companyRequest.getAddressLine2());
            company.setLogo(fileStrorageOperation.uploadFile(companyRequest.getMultipartFile()));
            company.setEmail(companyRequest.getEmail());
            company.setMobileNo(companyRequest.getMobileNo());
            company.setPassword(bCryptPasswordEncoder.encode(companyRequest.getPassword()));
            companyRepository.save(company);
            return "Updated successfully";
        }).orElseGet(() -> {
            if (companyRepository.existsByEmailIgnoreCaseAndIsDeleted(companyRequest.getEmail(), false)) {
                throw new EmailAlreadyExistsException("Email Already Exists");
            }
            if (companyRepository.existsByMobileNoIgnoreCaseAndIsDeleted(companyRequest.getMobileNo(), false)) {
                throw new EmailAlreadyExistsException("Mobile No Already Exists");
            }
            Company newCompany = Company.builder()
                    .companyName(companyRequest.getCompanyName())
                    .addressLine1(companyRequest.getAddressLine1())
                    .addressLine2(companyRequest.getAddressLine2())
                    .email(companyRequest.getEmail())
                    .logo(fileStrorageOperation.uploadFile(companyRequest.getMultipartFile()))
                    .isActive(true)
                    .isDeleted(false)
                    .mobileNo(companyRequest.getMobileNo())
                    .password(bCryptPasswordEncoder.encode(companyRequest.getPassword()))
                    .build();
            companyRepository.save(newCompany);
            emailConfig.sendEmail(companyRequest.getEmail(),"new Account created","Account is created for company : "+companyRequest.getCompanyName());
            return "Saved successfully";
        });
    }

    @Override
    public Object getCompanyByCompanyId(Long companyId) {
        return companyRepository.findById(companyId).map(company -> {
            company.setLogo(fileStrorageOperation.getFilePath(
                    company.getLogo()));
            return company;
        }).orElseThrow(() -> new CompanyNotFoundException("Company not found"));
    }

    @Override
    public Object deleteCompanyByCompanyId(Long companyId) {
        return companyRepository.findById(companyId).map(company ->{
            company.setIsDeleted(true);
            companyRepository.save(company);
            return "deleted successfully";
        }).orElseThrow(()->new CompanyNotFoundException("Company not found"));
    }

    @Override
    public Object changeStatusByCompanyId(Long companyId) {
        return companyRepository.findById(companyId).map(company -> {
            if (company.getIsActive()) {
                company.setIsActive(false);
            } else {
                company.setIsActive(true);
            }
            companyRepository.save(company);
            return "changed status successfully";
        });
    }

    @Override
    public Object saveOrUpdateCompanyDetails(CompanyDetailsRequest companyDetailsRequest) {
         return comapanyDetailsRepository.findById(companyDetailsRequest.getCompanyId()).map(companyDetails -> {
             companyDetails.setCompanyDesc(companyDetailsRequest.getCompanyDesc());
             companyDetails.setEmployeeCount(companyDetailsRequest.getEmployeeCount());
             companyDetails.setFacebook_profile(companyDetailsRequest.getFacebook_profile());
             companyDetails.setFoundedAt(companyDetailsRequest.getFoundedAt());
             companyDetails.setFounder(companyDetailsRequest.getFounder());
             companyDetails.setHeadquater(companyDetailsRequest.getHeadquater());
             companyDetails.setIndustry(companyDetailsRequest.getIndustry());
             companyDetails.setLinkedinProfile(companyDetailsRequest.getLinkedinProfile());
             companyDetails.setType(companyDetailsRequest.getType());
             companyDetails.setWebsite(companyDetailsRequest.getWebsite());
             companyRepository.findById(companyDetailsRequest.getCompanyId()).map(company ->{
                 companyDetails.setCompany(company);
                 return "";
             }).orElseThrow(() -> new CompanyNotFoundException("company not found"));
             comapanyDetailsRepository.save(companyDetails);
             return "Updated successfully";
         }).orElseGet(() -> {
             CompanyDetails companyDetails=new CompanyDetails();
             companyDetails.setCompanyDesc(companyDetailsRequest.getCompanyDesc());
             companyDetails.setEmployeeCount(companyDetailsRequest.getEmployeeCount());
             companyDetails.setFacebook_profile(companyDetailsRequest.getFacebook_profile());
             companyDetails.setFoundedAt(companyDetailsRequest.getFoundedAt());
             companyDetails.setFounder(companyDetailsRequest.getFounder());
             companyDetails.setHeadquater(companyDetailsRequest.getHeadquater());
             companyDetails.setIndustry(companyDetailsRequest.getIndustry());
             companyDetails.setLinkedinProfile(companyDetailsRequest.getLinkedinProfile());
             companyDetails.setType(companyDetailsRequest.getType());
             companyDetails.setWebsite(companyDetailsRequest.getWebsite());
             companyRepository.findById(companyDetailsRequest.getCompanyId()).map(company ->{
                 companyDetails.setCompany(company);
                 return "";
             }).orElseThrow(() -> new CompanyNotFoundException("company not found"));
             comapanyDetailsRepository.save(companyDetails);
             return "saved successfully";
         });

    }


}
