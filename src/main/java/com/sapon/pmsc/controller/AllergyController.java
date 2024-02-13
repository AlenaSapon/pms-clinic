package com.sapon.pmsc.controller;

import com.sapon.pmsc.model.Allergy;
import com.sapon.pmsc.repository.AllergyRepository;
import com.sapon.pmsc.repository.PatientRepository;
import com.sapon.pmsc.service.AllergyService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/v1")
public class AllergyController {

    private final AllergyService allergyService;

    private final AllergyRepository allergyRepository;

    private final PatientRepository patientRepository;

    public AllergyController(AllergyService allergyService, AllergyRepository allergyRepository, PatientRepository patientRepository) {
        this.allergyService = allergyService;
        this.allergyRepository = allergyRepository;
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patients/{id}/allergies")
    public ModelAndView getAllergiesByPatientId(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("allergies");
        mav.addObject("allergies", allergyRepository.findByPatientId(id));
        mav.addObject("patient", patientRepository.findById(id).orElseThrow());
        mav.addObject("newAllergy", new Allergy());
                return mav;
    }

    @GetMapping("/patients/{id}/allergiesCard")
    public ModelAndView getNewAllergiesByPatientId(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("fragments/allergiesCard");
        mav.addObject("allergiesCard", allergyRepository.findByPatientId(id));
        return mav;
    }

    @GetMapping("/{id}/allergy/newAllergy")
    public ModelAndView getAllergyModal(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("fragments/newAllergy");
       // mav.addObject("newAllergy", new Allergy());
        mav.addObject("patient", patientRepository.findById(id).orElseThrow());
        return mav;
    }

    //    @GetMapping("/allergies/{allergyId}")
//    public ResponseEntity<Allergy> findAllergyById(@PathVariable Long allergyId) {
//        Allergy allergy = allergyService.findAllergyById(allergyId);
//        return ResponseEntity.ok(allergy);
//    }


    @PostMapping("/allergy/save")
    public ResponseEntity<Allergy> addNewAllergy(@RequestParam("patientId") Long patientId,
                                                  Allergy allergyRequest,
                                                 HttpServletResponse response) throws IOException {
        Allergy allergy = patientRepository.findById(patientId).map(patient -> {
            allergyRequest.setPatient(patient);
            return allergyRepository.save(allergyRequest);
        }).orElseThrow(() -> new IllegalStateException("Not found Patient with id = " + patientId));
        response.sendRedirect("/api/v1/patients/" + patientId + "/allergies");
        return new ResponseEntity<>(allergy, HttpStatus.CREATED);
    }

    @GetMapping("/allergies/delete/{allergyId}")
    public ResponseEntity<HttpStatus> deleteAllergy(@PathVariable("allergyId") Long id) {
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
