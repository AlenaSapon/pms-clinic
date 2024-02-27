package com.sapon.pmsc.repository;

import com.sapon.pmsc.model.Clinic;
import com.sapon.pmsc.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClinicRepository extends JpaRepository<Clinic, String> {
    @Query("SELECT s FROM Clinic s where s.name = ?1")
    Optional<Clinic>findClinicByName(String name);


}
