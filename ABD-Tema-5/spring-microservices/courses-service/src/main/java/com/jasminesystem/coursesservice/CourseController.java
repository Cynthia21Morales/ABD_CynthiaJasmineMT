package com.jasminesystem.coursesservice;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    private final CourseRepository repository;

    public CourseController(CourseRepository repository) {
        this.repository = repository;
    }

    // =========================
    // OBTENER TODOS LOS CURSOS
    // =========================

    @GetMapping("/courses")
    public List<Course> getCourses() {
        return repository.findAll();
    }

    // =========================
    // GUARDAR CURSO
    // =========================

    @PostMapping("/courses")
    public Course saveCourse(@RequestBody Course course) {
        return repository.save(course);
    }

    // =========================
    // ACTUALIZAR CURSO
    // =========================

    @PutMapping("/courses/{id}")
    public Course updateCourse(
            @PathVariable Long id,
            @RequestBody Course course) {

        Course existing = repository.findById(id)
                .orElseThrow();

        existing.setName(course.getName());
        existing.setTeacherId(course.getTeacherId());

        return repository.save(existing);
    }

    // =========================
    // ELIMINAR CURSO
    // =========================

    @DeleteMapping("/courses/{id}")
    public String deleteCourse(@PathVariable Long id) {

        repository.deleteById(id);

        return "Curso eliminado correctamente";
    }

    // =========================
    // HORARIO DE ESTUDIANTE
    // =========================

    @GetMapping("/student-schedule/{id}")
    public StudentScheduleResponse getStudentSchedule(@PathVariable Long id) {

        if (id == 1) {
            return new StudentScheduleResponse(
                    "Jasmine Morales",
                    "Programacion Web",
                    "Carlos Gomez",
                    "7:00 AM",
                    "A-101"
            );
        }

        if (id == 2) {
            return new StudentScheduleResponse(
                    "Luis Daniel",
                    "Bases de Datos",
                    "Maria Lopez",
                    "9:00 AM",
                    "B-201"
            );
        }

        if (id == 3) {
            return new StudentScheduleResponse(
                    "Belen Lopez",
                    "Redes",
                    "Jose Ramirez",
                    "11:00 AM",
                    "C-301"
            );
        }

        if (id == 4) {
            return new StudentScheduleResponse(
                    "Angel Ortiz",
                    "Matematicas",
                    "Ana Torres",
                    "1:00 PM",
                    "D-401"
            );
        }

        if (id == 5) {
            return new StudentScheduleResponse(
                    "Bryan Lopez",
                    "Inteligencia Artificial",
                    "Luis Herrera",
                    "3:00 PM",
                    "E-501"
            );
        }

        return new StudentScheduleResponse(
                "No encontrado",
                "No encontrado",
                "No encontrado",
                "--",
                "--"
        );
    }
}