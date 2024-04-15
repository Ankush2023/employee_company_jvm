package com.epms.company.model;

import com.epms.company.utils.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "epms_employee_leave_balance")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLeaveBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_leave_history_id")
    private Long employeeLeaveHistoryId;

    @Column(name = "leave_type")
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    @Column(name = "total_leave_balance")
    private Long totalLeaveBalance;

    @Column(name = "used_leave_balance")
    private Long usedLeaveBalance;

    @Column(name = "avaible_leave_balance")
    private Long avaibleLeaveBalance;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
