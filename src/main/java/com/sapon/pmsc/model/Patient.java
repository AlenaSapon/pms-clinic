package com.sapon.pmsc.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(
        name="patient",
        uniqueConstraints ={
                @UniqueConstraint(name ="patient_pid_unique", columnNames = "pid"),
                @UniqueConstraint(name ="patient_email_unique", columnNames = "email"),
        }
)
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
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

    @Column(name = "pid", nullable = false)
    private String pid;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Transient
    private Integer age;


    private String email;

    private String phone;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private Boolean consern;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Allergy> allergies = new HashSet<>();

    public Patient(String pid,
                   String firstName,
                   String lastName,
                   String gender,
                   LocalDate dob,
                   String email,
                   String phone,
                   String address,
                   String city,
                   String state,
                   String zipcode,
                   Boolean consern) {
        this.pid = pid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.consern = consern;
    }

    public Patient(Long id,
                   String pid,
                   String firstName,
                   String lastName,
                   String gender,
                   LocalDate dob,
                   String email,
                   String phone,
                   String address,
                   String city,
                   String state,
                   String zipcode,
                   Boolean consern) {
        this.id = id;
        this.pid = pid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.consern = consern;
    }

    public Integer getAge(){
        return Period.between(dob, LocalDate.now()).getYears();
}

}