package com.sapon.pmsc.repository;

import com.sapon.pmsc.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository
        extends JpaRepository<Patient, Long> {
    @Query("SELECT s FROM Patient s where s.email = ?1")
    Optional<Patient>findPatientByEmail(String email);

    @Query("SELECT s FROM Patient s where s.pid = ?1")
    Optional<Patient>findPatientByPid(String pid);

    }

