package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args ->{
            Student mariam = new Student(
                "Mariam",
                "mariam.jamal@gmail.com",
                LocalDate.of(2000,Month.JANUARY,5),
                21
            );

            Student alex = new Student(
                "alex",
                "alex@gmail.com",
                LocalDate.of(2005,Month.JANUARY,5),
                21
            );
            repository.saveAll(
                List.of(mariam, alex)
            );
         };
    }
}
