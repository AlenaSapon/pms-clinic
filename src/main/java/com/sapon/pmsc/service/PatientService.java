package com.sapon.pmsc.service;

import com.sapon.pmsc.helper.BusinessLogMessage;
import com.sapon.pmsc.helper.BusinessMessage;
import com.sapon.pmsc.model.Patient;
import com.sapon.pmsc.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public Object addNewPatient(Patient patient) {
        Optional<Patient> patientByEmail = patientRepository
                .findPatientByEmail(patient.getEmail());

        if (patientByEmail.isPresent()) {
            log.error(BusinessLogMessage.Patient.PATIENT_ALREADY_EXISTS_EMAIL);
            throw new IllegalStateException(BusinessMessage.Patient.PATIENT_ALREADY_EXISTS_EMAIL);
        }

        Optional<Patient> patientByPid = patientRepository
                .findPatientByPid(patient.getPid());

        if (patientByPid.isPresent()) {
            log.error(BusinessLogMessage.Patient.PATIENT_ALREADY_EXISTS_PID);
            throw new IllegalStateException(BusinessMessage.Patient.PATIENT_ALREADY_EXISTS_PID);
        }

        patientRepository.save(patient);
        log.info(BusinessLogMessage.Patient.PATIENT_CREATED);
        return patient;
    }

    public void deletePatient(Long patientId) {
        boolean exists = patientRepository.existsById(patientId);
        if (!exists) {

            throw new IllegalStateException(BusinessMessage.Patient.PATIENT_NOT_FOUND);
        }
        patientRepository.deleteById(patientId);
        log.info(BusinessLogMessage.Patient.PATIENT_DELETED);
    }

    @Transactional
    public void updatePatient(Long patientId,
                              String pid,
                              String firstName,
                              String lastName,
                              LocalDate dob,
                              String gender,
                              String phone,
                              String email,
                              String address,
                              String city,
                              String state,
                              String zipcode,
                              boolean consern) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalStateException(BusinessMessage.Patient.PATIENT_NOT_FOUND));

        if (firstName != null &&
                !firstName.isEmpty() &&
                !Objects.equals(patient.getFirstName(), firstName)) {
            patient.setFirstName(firstName);
        }

        if (email != null &&
                !email.isEmpty() &&
                !Objects.equals(patient.getEmail(), email)) {
            Optional<Patient> patientOptional = patientRepository
                    .findPatientByEmail(email);
            if (patientOptional.isPresent()) {
                log.error(BusinessLogMessage.Patient.PATIENT_ALREADY_EXISTS_EMAIL);
                throw new IllegalStateException(BusinessMessage.Patient.PATIENT_ALREADY_EXISTS_EMAIL);
            }
            patient.setEmail(email);
        }

        if (pid != null &&
                !pid.isEmpty() &&
                !Objects.equals(patient.getPid(), pid)) {
            Optional<Patient> patientOptional = patientRepository
                    .findPatientByPid(pid);
            if (patientOptional.isPresent()) {
                log.error(BusinessLogMessage.Patient.PATIENT_ALREADY_EXISTS_PID);
                throw new IllegalStateException(BusinessMessage.Patient.PATIENT_ALREADY_EXISTS_PID);
            }
            patient.setPid(pid);
        }

        if (lastName != null &&
                !lastName.isEmpty() &&
                !Objects.equals(patient.getLastName(), lastName)) {
            patient.setLastName(lastName);
        }

        if (dob != null &&
                !dob.toString().isEmpty() &&
                !Objects.equals(patient.getDob(), dob)) {
            patient.setDob(dob);
        }

        if (gender != null &&
                !gender.isEmpty() &&
                !Objects.equals(patient.getGender(), gender)) {
            patient.setGender(gender);
        }

        if (phone != null &&
                !phone.isEmpty() &&
                !Objects.equals(patient.getPhone(), phone)) {
            patient.setPhone(phone);
        }

        if (address != null &&
                !address.isEmpty() &&
                !Objects.equals(patient.getAddress(), address)) {
            patient.setAddress(address);
        }

        if (city != null &&
                !city.isEmpty() &&
                !Objects.equals(patient.getCity(), city)) {
            patient.setCity(city);
        }

        if (state != null &&
                !state.isEmpty() &&
                !Objects.equals(patient.getState(), state)) {
            patient.setState(state);
        }

        if (zipcode != null &&
                !zipcode.isEmpty() &&
                !Objects.equals(patient.getZipcode(), zipcode)) {
            patient.setZipcode(zipcode);
        }

        if (!Objects.equals(patient.getConsern(), consern)) {
            patient.setConsern(consern);
        }

        log.info(BusinessLogMessage.Patient.PATIENT_UPDATED);
    }

    public Patient findPatientById(final Long id) {
        log.error(BusinessLogMessage.Patient.PATIENT_NOT_FOUND);
        return patientRepository.findById(id).orElseThrow(() ->
                new IllegalStateException(BusinessMessage.Patient.PATIENT_NOT_FOUND));
    }


}