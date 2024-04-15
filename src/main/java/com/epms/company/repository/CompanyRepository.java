package com.epms.company.repository;

import com.epms.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {

    Optional<Company> findByEmailIgnoreCaseAndIsDeleted(String username, boolean b);

    boolean existsByEmailIgnoreCaseAndIsDeleted(String email, boolean b);

    boolean existsByMobileNoIgnoreCaseAndIsDeleted(String mobileNo, boolean b);

    boolean existsByEmailIgnoreCaseAndIsDeletedAndCompanyIdNotIn(String email, boolean b, List<Long> companyIds);

    boolean existsByMobileNoIgnoreCaseAndIsDeletedAndCompanyIdNotIn(String mobileNo, boolean b, List<Long> companyIds);

    List<Company> findAllByIsDeleted(boolean b);
}
