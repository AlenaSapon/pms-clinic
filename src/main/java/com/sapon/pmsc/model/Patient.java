package com.sapon.pmsc.model;

import com.sapon.pmsc.service.AttributeEncryptor;
import jakarta.persistence.*;
import lombok.*;
import org.aspectj.bridge.IMessage;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@ToString
@Entity
@Table(
        name = "patient",
        uniqueConstraints = {
                @UniqueConstraint(name = "patient_pid_unique", columnNames = "pid"),
                @UniqueConstraint(name = "patient_email_unique", columnNames = "email"),
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

    @Convert(converter = AttributeEncryptor.class)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Convert(converter = AttributeEncryptor.class)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Convert(converter = AttributeEncryptor.class)
    @Column(name = "gender")
    private String gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dob")
    private LocalDate dob;

    @Transient
    private Integer age;

    @Convert(converter = AttributeEncryptor.class)
    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "consern")
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

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

}
