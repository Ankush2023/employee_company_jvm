package com.epms.company.model;

import com.epms.company.utils.WorkStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "epms_employee_work_status")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeWorkStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_work_status_id")
    private Long employeeWorkStatusId;

    @Column(name = "leave_type")
    @Enumerated(EnumType.STRING)
    private WorkStatus workStatus;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
