package com.davillafer.students.repository;

import com.davillafer.students.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentsRepository repository) {
        return args -> {
            Student luis = new Student(
                    1L,
                    "Luis",
                    "luis@gmail.com",
                    LocalDate.of(1985, Month.JANUARY, 5)
            );
            Student raul = new Student(
                    2L,
                    "Raul",
                    "raul@gmail.com",
                    LocalDate.of(1990, Month.JUNE, 23)
            );
            repository.saveAll(
                    List.of(luis, raul)
            );
        };
    }
}
