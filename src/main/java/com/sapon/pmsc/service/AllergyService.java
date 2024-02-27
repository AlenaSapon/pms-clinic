package com.sapon.pmsc.service;

import com.sapon.pmsc.helper.BusinessLogMessage;
import com.sapon.pmsc.helper.BusinessMessage;
import com.sapon.pmsc.model.Allergy;
import com.sapon.pmsc.model.Patient;
import com.sapon.pmsc.repository.AllergyRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
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

    public void addAllergyToPatient(Long allergyId, Long patientId) {
        Allergy allergy = findAllergyById(allergyId);
        allergy.setPatient(patientService.findPatientById(patientId));
        allergyRepository.save(allergy);
    }

    public void deleteAllergy(Long allergyId) {
        boolean exists = allergyRepository.existsById(allergyId);
        if (!exists) {
            log.error(BusinessLogMessage.Allergy.ALLERGY_DELETED);
            throw new IllegalStateException(BusinessMessage.Allergy.ALLERGY_NOT_FOUND);
        }
        allergyRepository.deleteById(allergyId);
    }

    public Allergy findAllergyById(final Long id) {
        return allergyRepository.findById(id).orElseThrow(() ->
                new IllegalStateException(BusinessMessage.Allergy.ALLERGY_NOT_FOUND));
    }

    @Transactional
    public void updateAllergy(Long allergyId,
                              String title,
                              String reaction) {
        Allergy allergy = allergyRepository.findById(allergyId)
                .orElseThrow(() -> new IllegalStateException(BusinessMessage.Allergy.ALLERGY_NOT_FOUND));

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
