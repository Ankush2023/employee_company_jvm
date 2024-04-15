package com.epms.company.model;

import com.epms.company.utils.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "epms_employee_leave_master")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLeaveMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_leave_master_id")
    private Long employeeLeaveMasterId;

    @Column(name = "leave_type")
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    @Column(name = "leave_balance")
    private Long leaveBalance;

}
