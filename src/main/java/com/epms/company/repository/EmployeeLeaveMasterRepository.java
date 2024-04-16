package com.epms.company.repository;

import com.epms.company.model.EmployeeLeaveMaster;
import com.epms.company.utils.LeaveType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.DoubleStream;

public interface EmployeeLeaveMasterRepository extends JpaRepository<EmployeeLeaveMaster,Long> {
    Page<EmployeeLeaveMaster> findAllByIsDeleted(boolean b, Pageable pageable);

    Page<EmployeeLeaveMaster> findAllByIsDeletedAndLeaveType(boolean b, LeaveType leaveType1, Pageable pageable);

    Optional<EmployeeLeaveMaster> findByEmployeeLeaveMasterIdAndLeaveType(Long employeeLeaveMasterId, LeaveType leaveType);
}
