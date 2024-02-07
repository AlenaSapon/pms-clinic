package com.sapon.pmsc.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;

@Getter
@Setter
@ToString
@Entity
@Table
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
    private String pid;
    private String firstName;
    private String lastName;
    private String gender;
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
