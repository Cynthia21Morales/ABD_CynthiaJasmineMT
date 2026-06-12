package com.jasminesystem.coursesservice;

import org.springframework.web.bind.annotation.*;

@RestController
public class EnrollmentController {

    private final EnrollmentRepository repository;

    public EnrollmentController(EnrollmentRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/enrollments")
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {

        return repository.save(enrollment);

    }
}