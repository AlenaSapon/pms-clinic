package com.sapon.pmsc.service;

import com.sapon.pmsc.helper.BusinessLogMessage;
import com.sapon.pmsc.model.Clinic;
import com.sapon.pmsc.model.Patient;
import com.sapon.pmsc.repository.ClinicRepository;
import com.sapon.pmsc.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClinicService {
    private final ClinicRepository clinicRepository;

    @Autowired
    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public List<Clinic> getClinics() {
        return clinicRepository.findAll();
    }

    public Object addNewClinic(Clinic clinic) {
        Optional<Clinic> clinicName = clinicRepository
                .findClinicByName(clinic.getName());

        if (clinicName.isPresent()) {
            log.error(BusinessLogMessage.Clinic.CLINIC_ALREADY_EXISTS + clinicName);
            throw new IllegalStateException("Such clinic name is taken");
        }
        clinicRepository.save(clinic);
        log.info(BusinessLogMessage.Clinic.CLINIC_CREATED);
        return clinic;
    }

}

