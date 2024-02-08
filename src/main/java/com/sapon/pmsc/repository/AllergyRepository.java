package com.sapon.pmsc.repository;

import com.sapon.pmsc.model.Allergy;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {
    List<Allergy> findByPatientId(Long patientId);

}
