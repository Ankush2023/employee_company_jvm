package com.epms.company.repository;

import com.epms.company.model.EmployeeLeaveMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeLeaveMasterRepository extends JpaRepository<EmployeeLeaveMaster,Long> {
    Object findAllByIsDeleted(boolean b);
}
