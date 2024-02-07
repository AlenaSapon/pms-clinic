package com.sapon.pmsc.controller;

import com.sapon.pmsc.model.Patient;
import com.sapon.pmsc.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/patients")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> findPatientById(@PathVariable Long patientId) {
        Patient patient = patientService.findPatientById(patientId);
        return ResponseEntity.ok(patient);
    }

    @PostMapping
    public void registerNewPatient(@RequestBody Patient patient) {
        patientService.addNewPatient(patient);
    }

    @DeleteMapping(path = "{patientId}")
    public void deletePatient(@PathVariable("patientId") Long patientId) {
        patientService.deletePatient(patientId);
    }

    @PutMapping(path = "{patientId}")
    public void updatePatient(
            @PathVariable("patientId") Long patientId,
            @RequestParam(required = false) String pid,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) LocalDate dob,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String zipcode,
            @RequestParam(required = false) boolean consern
    ) {
        patientService.updatePatient(patientId,
                pid,
                firstName,
                lastName,
                dob,
                gender,
                phone,
                email,
                address,
                city,
                state,
                zipcode,
                consern
        );
    }
}
