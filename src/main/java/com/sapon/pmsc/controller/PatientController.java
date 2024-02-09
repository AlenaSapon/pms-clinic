package com.sapon.pmsc.controller;

import com.sapon.pmsc.model.Patient;
import com.sapon.pmsc.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public ModelAndView getPatients() {
        ModelAndView mav = new ModelAndView("patients");
        mav.addObject("patients", patientService.getPatients());
        return mav;
    }

    @GetMapping("/{id}")
    public ModelAndView getPatientById(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("patient");
        mav.addObject("patient", patientService.findPatientById(id));
        return mav;
    }

//    @GetMapping("/patients/new")
//    public String addPatient(Model model) {
//        Patient patient = new Patient();
//        model.addAttribute("patients", patient);
//        return "patients";
//    }
    @PostMapping()
    public void addPatient(@ModelAttribute Patient patient) {
        patientService.addNewPatient(patient);
            }
//    @PostMapping
//    public void registerNewPatient(@RequestBody Patient patient) {
//        patientService.addNewPatient(patient);
//    }

//    @DeleteMapping(path = "{patientId}")
//    public void deletePatient(@PathVariable("patientId") Long patientId) {
//        patientService.deletePatient(patientId);
//    }

    @DeleteMapping("/delete/{id}")
    public ModelAndView deletePatient(@PathVariable("id") Long id) {
        patientService.deletePatient(id);
        return new ModelAndView("patients");
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