package com.sapon.pmsc.service;

import com.sapon.pmsc.model.Patient;
import com.sapon.pmsc.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public void addNewPatient(Patient patient) {
        Optional<Patient> patientByEmail = patientRepository
                .findPatientByEmail(patient.getEmail());

        if (patientByEmail.isPresent()) {
            throw new IllegalStateException("email is taken");
        }

        Optional<Patient> patientByPid = patientRepository
                .findPatientByPid(patient.getPid());

        if (patientByPid.isPresent()) {
            throw new IllegalStateException("pid is taken");
        }

        patientRepository.save(patient);
    }

    public void deletePatient(Long patientId) {
        boolean exists = patientRepository.existsById(patientId);
        if (!exists) {
            throw new IllegalStateException("patient with id "
                    + patientId + " doesn't exist");
        }
        patientRepository.deleteById(patientId);
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
                .orElseThrow(() -> new IllegalStateException(
                        "patient with id " + patientId + " doesn't exist"));

        if (firstName != null &&
                firstName.length() > 0 &&
                !Objects.equals(patient.getFirstName(), firstName)) {
            patient.setFirstName(firstName);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(patient.getEmail(), email)) {
            Optional<Patient> patientOptional = patientRepository
                    .findPatientByEmail(email);
            if (patientOptional.isPresent()) {
                throw new IllegalStateException("email is already taken");
            }
            patient.setEmail(email);
        }

        if (pid != null &&
                !pid.isEmpty() &&
                !Objects.equals(patient.getPid(), pid)) {
            Optional<Patient> patientOptional = patientRepository
                    .findPatientByPid(pid);
            if (patientOptional.isPresent()) {
                throw new IllegalStateException("PID is already taken");
            }
            patient.setPid(pid);}

        if (lastName != null &&
                !lastName.isEmpty() &&
                !Objects.equals(patient.getLastName(), lastName)) {
        patient.setLastName(lastName);}

        if (dob != null &&
                !dob.toString().isEmpty() &&
                !Objects.equals(patient.getLastName(), lastName)) {
        patient.setDob(dob);}

        if (gender != null &&
                !gender.isEmpty() &&
                !Objects.equals(patient.getGender(), gender)) {
        patient.setGender(gender);}

        if (phone != null &&
                !phone.isEmpty() &&
                !Objects.equals(patient.getPhone(), phone)) {
        patient.setPhone(phone);}

        if (address != null &&
                !address.isEmpty() &&
                !Objects.equals(patient.getAddress(), address)) {
        patient.setAddress(address);}

        if (city != null &&
                !city.isEmpty() &&
                !Objects.equals(patient.getCity(), city)) {
        patient.setCity(city);}

        if (state != null &&
                !state.isEmpty() &&
                !Objects.equals(patient.getState(), state)) {
        patient.setState(state);}

        if (zipcode != null &&
                !zipcode.isEmpty() &&
                !Objects.equals(patient.getZipcode(), zipcode)) {
        patient.setZipcode(zipcode);}

        if (!Objects.equals(patient.getConsern(), consern)) {
        patient.setConsern(consern);
    }
    }
}
