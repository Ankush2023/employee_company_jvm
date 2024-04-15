package com.epms.company.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "epms_company_details")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_datails_id")
    private Long companyDetailsId;

    @Column(name = "company_desc")
    private String companyDesc;

    @Column(name = "industry")
    private String industry;

    @Column(name = "type")
    private String type;

    @Column(name = "founded_at")
    private LocalDateTime foundedAt;

    @Column(name = "founder")
    private String founder;

    @Column(name = "headquater")
    private String headquater;

    @Column(name = "employee_count")
    private String employeeCount;

    @Column(name = "website")
    private String website;

    @Column(name = "linkedin_profile")
    private String linkedinProfile;

    @Column(name = "facebook_profile")
    private String facebook_profile;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
