package com.epms.company.model;

import com.epms.company.utils.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "epms_employee_leave_history")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeLeaveHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_leave_history_id")
    private Long employeeLeaveHistoryId;

    @Column(name = "leave_type")
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "is_approved")
    private Boolean isApproved ;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
