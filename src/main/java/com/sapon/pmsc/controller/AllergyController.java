package com.sapon.pmsc.controller;

import com.sapon.pmsc.model.Allergy;
import com.sapon.pmsc.model.Patient;
import com.sapon.pmsc.repository.AllergyRepository;
import com.sapon.pmsc.repository.PatientRepository;
import com.sapon.pmsc.service.AllergyService;
import com.sapon.pmsc.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
public class AllergyController {


    private final AllergyService allergyService;

    private final AllergyRepository allergyRepository;

    private final PatientService patientService;

    private final PatientRepository patientRepository;

    public AllergyController(AllergyService allergyService, AllergyRepository allergyRepository, PatientService patientService, PatientRepository patientRepository) {
        this.allergyService = allergyService;
        this.allergyRepository = allergyRepository;
        this.patientService = patientService;
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patients/{patientId}/allergies")
    public List<Allergy> getAllAllergiesByPatientId(@PathVariable("patientId") Long patientId) {
        return allergyRepository.findByPatientId(patientId);
    }

    @GetMapping("/allergies/{allergyId}")
    public ResponseEntity<Allergy> findPatientById(@PathVariable Long allergyId) {
        Allergy allergy = allergyService.findAllergyById(allergyId);
        return ResponseEntity.ok(allergy);
    }

    @PostMapping("/patients/{patientId}/allergies")
    public ResponseEntity<Allergy> addNewAllergy(@PathVariable Long patientId,
                                                 @RequestBody Allergy allergyRequest) {
        Allergy allergy = patientRepository.findById(patientId).map(patient -> {
            allergyRequest.setPatient(patient);
            return allergyRepository.save(allergyRequest);
        }).orElseThrow(() -> new IllegalStateException("Not found Tutorial with id = " + patientId));;
        return new ResponseEntity<>(allergy, HttpStatus.CREATED);
    }


    @DeleteMapping("/allergies/{id}")
    public ResponseEntity<HttpStatus> deleteAllergy(@PathVariable("id") Long id) {
        allergyRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(path = "/allergies/{allergyId}")
    public void updateAllergy(
            @PathVariable("allergyId") Long allergyId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String reaction) {
        allergyService.updateAllergy(allergyId,
                title,
                reaction
        );
    }

}
