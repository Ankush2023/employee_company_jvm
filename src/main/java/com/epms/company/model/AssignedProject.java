package com.epms.company.model;

import com.epms.company.utils.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "epms_assigned_project")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignedProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assigned_project_id")
    private Long assignedProjectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "project_type")
    private String projectType;

    @Column(name = "project_assigned_date")
    private LocalDate projectAssignedDate;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "project_status")
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;

    @OneToOne
    @JoinColumn(name = "project_manager_id")
    private Employee projectManager;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
