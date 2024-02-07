package com.sapon.pmsc.repository;

import com.sapon.pmsc.model.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {
}
