package com.sapon.pmsc.controller;

import com.sapon.pmsc.model.Allergy;
import com.sapon.pmsc.model.Patient;
import com.sapon.pmsc.service.AllergyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/patients/{patientId}/allergies")
public class AllergyController {
    private final AllergyService allergyService;

    public AllergyController(AllergyService allergyService) {
        this.allergyService = allergyService;
    }

    @GetMapping
    public List<Allergy> getAllergies(@PathVariable Long patientId) {
        return allergyService.getAllergies();
    }

    @GetMapping("/{allergyId}")
    public ResponseEntity<Allergy> findPatientById(@PathVariable Long allergyId,
                                                   @PathVariable Long patientId) {
        Allergy allergy = allergyService.findAllergyById(allergyId);
        return ResponseEntity.ok(allergy);
    }

    @PostMapping
    public void addNewAllergy(@RequestBody Allergy allergy,
                              @PathVariable Long patientId) {
        allergyService.addNewAllergy(allergy, patientId);
    }

    @DeleteMapping(path = "{allergyId}")
    public void deleteAllergy(@PathVariable("allergyId") Long allergyId,
                              @PathVariable String patientId) {
        allergyService.deleteAllergy(allergyId);
    }

    @PutMapping(path = "{allergyId}")
    public void updateAllergy(
            @PathVariable("allergyId") Long allergyId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String reaction,
            @PathVariable(required = false) String patientId) {
        allergyService.updateAllergy(allergyId,
                title,
                reaction
        );
    }
}
