package com.sapon.pmsc.config;

import com.sapon.pmsc.controller.PatientController;
import com.sapon.pmsc.model.Allergy;
import com.sapon.pmsc.model.Patient;
import com.sapon.pmsc.repository.AllergyRepository;
import com.sapon.pmsc.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;


//public class AllergyConfig {
//        CommandLineRunner commandLineRunner(AllergyRepository allergyRepository) {
//        return arg -> {
//                     Allergy clarithromicin = new Allergy(
//                    "clarithromicin",
//                    "it is reaction",
//                    1L
//
//            );
//        };
//    }}