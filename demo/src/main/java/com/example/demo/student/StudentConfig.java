package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
           Student a1 = new Student("Yogendra Singh", LocalDate.of(2005,01,01),"ysshekhawat1098@gamil.com");
           Student a12 = new Student("Yogesh Singh", LocalDate.of(2000,01,01),"ysshekhawat@gamil.com");

           studentRepository.saveAll(List.of(a1,a12));
        };
    }
}
