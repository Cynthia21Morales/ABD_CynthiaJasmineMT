package com.jasminesystem.coursesservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class CourseController {

    @GetMapping("/courses")
    public List<Course> getCourses() {


        return List.of(

                new Course(1L, "Programacion Web", 1L),
                new Course(2L, "Bases de Datos", 2L),
                new Course(3L, "Redes", 3L),
                new Course(4L, "Matematicas", 4L),
                new Course(5L, "Inteligencia Artificial", 5L)

        );
    }

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
        if (id == 6) {
            return new StudentScheduleResponse(
                    "Daniel Silas",
                    "Programacion Web",
                    "Carlos Gomez",
                    "7:00 AM",
                    "A-101"
            );
        }

        if (id == 7) {
            return new StudentScheduleResponse(
                    "Eddi Castillo",
                    "Bases de Datos",
                    "Maria Lopez",
                    "9:00 AM",
                    "B-201"
            );
        }

        if (id == 8) {
            return new StudentScheduleResponse(
                    "Vanni Gutierrez",
                    "Redes",
                    "Jose Ramirez",
                    "11:00 AM",
                    "C-301"
            );
        }

        if (id == 9) {
            return new StudentScheduleResponse(
                    "Emanuel",
                    "Matematicas",
                    "Ana Torres",
                    "1:00 PM",
                    "D-401"
            );
        }

        if (id == 10) {
            return new StudentScheduleResponse(
                    "cynthia Morales",
                    "Programacion Web",
                    "Carlos Gomez",
                    "7:00 PM",
                    "A-101"
            );
        }

        if (id == 11) {
            return new StudentScheduleResponse(
                    "Kevin Morales",
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