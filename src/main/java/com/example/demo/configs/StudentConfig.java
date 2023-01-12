package com.example.demo.configs;

import com.example.demo.entities.Student;
import com.example.demo.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


//@Configuration
//public class StudentConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(StudentRepository repository){
//        return args -> {
//            Student Demak = new Student(
//                    // The ID's will be automatically Generated for us
//                    "Demak",
//                    "demak@gmail.com",
//                    LocalDate.of(2000, Month.APRIL, 4)
//            );
//            Student Patel = new Student(
//                    "Patel",
//                    "patel@gmail.com",
//                    LocalDate.of(2004, Month.APRIL, 4)
//            );
//            repository.saveAll(
//                    List.of(Demak, Patel)
//            );
//        };
//    }
//}
