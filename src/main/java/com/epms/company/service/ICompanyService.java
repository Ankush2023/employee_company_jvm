package com.epms.company.service;

import com.epms.company.model.Company;
import org.springframework.security.core.userdetails.UserDetails;

public interface ICompanyService {
    UserDetails loadUserByUsername(String email);

    Company findByEmail(String username);
}
