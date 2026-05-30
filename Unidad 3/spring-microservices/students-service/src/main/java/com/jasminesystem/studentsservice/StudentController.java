package com.jasminesystem.studentsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/students")
    public List<Student> getStudents() {
        return repository.findAll();
    }

    @PostMapping("/students")
    public Student saveStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    @GetMapping("/students/courses")
    public String getCoursesFromService() {

        return restTemplate.getForObject(
                "http://COURSES-SERVICE/courses",
                String.class
        );
    }
}