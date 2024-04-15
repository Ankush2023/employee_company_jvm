package com.epms.company.service.serviceimpl;

import com.epms.company.model.Company;
import com.epms.company.repository.CompanyRepository;
import com.epms.company.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class companyServiceimpl implements ICompanyService, UserDetailsService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return companyRepository.findByEmailIgnoreCaseAndIsDeleted(s, false)
                .map(company -> new User(company.getEmail(), company.getPassword(), getAuthority(company)))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + s));
    }


    @Override
    public Company findByEmail(String username) {
        return companyRepository.findByEmailIgnoreCaseAndIsDeleted(username, false).orElse(new Company());
    }


    private Collection<? extends GrantedAuthority> getAuthority(Company company) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + "Admin"));
        return authorities;
    }
}
