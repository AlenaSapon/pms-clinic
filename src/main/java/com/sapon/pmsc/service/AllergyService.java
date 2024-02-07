package com.sapon.pmsc.service;

import com.sapon.pmsc.model.Allergy;
import com.sapon.pmsc.model.Patient;
import com.sapon.pmsc.repository.AllergyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AllergyService {

    private final AllergyRepository allergyRepository;
    private final PatientService patientService;

    @Autowired
    public AllergyService(AllergyRepository allergyRepository, PatientService patientService) {
        this.allergyRepository = allergyRepository;
        this.patientService = patientService;
    }

    public List<Allergy> getAllergies() {
        return allergyRepository.findAll();
    }

    public void addNewAllergy(Allergy allergy, Long patientId) {
        allergyRepository.save(allergy);
        allergy.setPatient(patientService.findPatientById(patientId));
    }

    public void deleteAllergy(Long allergyId) {
        boolean exists = allergyRepository.existsById(allergyId);
        if (!exists) {
            throw new IllegalStateException("allergy with id "
                    + allergyId + " doesn't exist in DB");
        }
        allergyRepository.deleteById(allergyId);
    }

    public Allergy findAllergyById(final Long id) {
        return allergyRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("allergy with this id" + id + " isn't found"));
    }

    @Transactional
    public void updateAllergy(Long allergyId,
                              String title,
                              String reaction) {
        Allergy allergy = allergyRepository.findById(allergyId)
                .orElseThrow(() -> new IllegalStateException(
                        "allergy with id " + allergyId + " doesn't exist"));

        if (title != null &&
                !title.isEmpty() &&
                !Objects.equals(allergy.getTitle(), title)) {
            allergy.setTitle(title);
        }

        if (reaction != null &&
                !reaction.isEmpty() &&
                !Objects.equals(allergy.getReaction(), reaction)) {
            allergy.setReaction(reaction);
        }
    }
    }
