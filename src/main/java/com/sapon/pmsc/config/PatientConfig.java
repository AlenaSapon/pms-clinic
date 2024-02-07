package com.sapon.pmsc.config;

import com.sapon.pmsc.model.Allergy;
import com.sapon.pmsc.model.Patient;
import com.sapon.pmsc.repository.AllergyRepository;
import com.sapon.pmsc.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class PatientConfig {

    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return arg -> {

            Patient larionova = new Patient(
                    "pid-1",
                    "olga",
                    "larionova",
                    "F",
                    LocalDate.of(2000, Month.JANUARY, 5),
                    "olarionova@gmail.com",
                    "+375447225158",
                    "st. Green 5/1-5",
                    "Minsk",
                    "Minskaya",
                    "220002",
                    true

            );
            Patient lapin = new Patient(
                    "pid-2",
                    "oleg",
                    "lapin",
                    "M",
                    LocalDate.of(2005, Month.JANUARY, 5),
                    "olapin@gmail.com",
                    "+375291112233",
                    "st. Red 5/1-5",
                    "Minsk",
                    "Minskaya",
                    "220002",
                    true
            );

            patientRepository.saveAll(
                    List.of(larionova, lapin)
            );
        };
    }
}
