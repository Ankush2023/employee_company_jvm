package com.epms.company.repository;

import com.epms.company.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    boolean existsByEmployeeCodeAndIsDeleted(String employeeCode, boolean b);

    boolean existsByEmailAndIsDeleted(String email, boolean b);

    boolean existsByEmailAndIsDeletedAndEmployeeIdNotIn(String email, boolean b, List<Long> employeeids);

    boolean existsByEmployeeCodeAndIsDeletedAndEmployeeIdNotIn(String employeeCode, boolean b, List<Long> employeeids);
}
