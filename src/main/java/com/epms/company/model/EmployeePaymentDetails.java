package com.epms.company.model;

import com.epms.company.utils.JobType;
import com.epms.company.utils.WorkMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "epms_employee_payment_details")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_payment_details_id")
    private Long employeePaymentDetailsId;

    @Column(name = "ctc")
    private Double ctc;

    @Column(name = "basic")
    private Double basic;

    @Column(name = "da")
    private Double da;

    @Column(name = "hra")
    private Double hra;

    @Column(name = "c_allowance")
    private Double cAllowance;

    @Column(name = "professional_tax")
    private Double professionalTax;

    @Column(name = "gratuity")
    private Double gratuity;

    @Column(name = "employee_welfare")
    private Double employeeWelfare;

    @Column(name = "provident_fund")
    private Double provident_fund;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
