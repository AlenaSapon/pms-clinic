package com.sapon.pmsc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @SequenceGenerator(
            name = "patient_sequence",
            sequenceName = "patient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_sequence"
    )
    private Long id;

    @Column(length = 64)
    private String firstName;

    @Column(length = 64)
    private String lastName;

    @Column(length = 64)
    private String username;

    @Column(length = 64)
    private String password;

    private String email;

    @Column(length = 64)
    private String phone;

    private Boolean isActive;

    @OneToMany(mappedBy = "employee")
    private List<HasRole> hasRoles;

    @OneToMany(mappedBy = "employee")
    private List<InDepartment> inDepartments;
}

