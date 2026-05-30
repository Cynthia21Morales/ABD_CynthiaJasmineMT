package com.jasminesystem.studentsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class StudentController {

    private static final Logger log =
            LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/students")
    public List<Student> getStudents() {

        log.info("Se está obteniendo la lista de estudiantes");

        return repository.findAll();
    }

    @PostMapping("/students")
    public Student saveStudent(@RequestBody Student student) {

        log.info("Guardando estudiante: " + student.getName());

        Student saved = repository.save(student);

        log.info("Estudiante guardado correctamente con ID: " + saved.getId());

        return saved;
    }

    @GetMapping("/students/courses")
    public String getCoursesFromService() {

        log.info("Consultando cursos desde courses-service");

        String response = restTemplate.getForObject(
                "http://COURSES-SERVICE/courses",
                String.class
        );

        log.info("Respuesta recibida desde courses-service");

        return response;
    }
}