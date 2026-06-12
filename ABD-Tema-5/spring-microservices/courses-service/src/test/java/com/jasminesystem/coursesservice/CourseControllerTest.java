package com.jasminesystem.coursesservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseControllerTest {

    private CourseController controller = new CourseController();

    @Test
    void testGetStudentSchedule() {

        StudentScheduleResponse result = controller.getStudentSchedule(1L);

        assertEquals("Jasmine Morales", result.getStudentName());
        assertEquals("Programacion Web", result.getCourseName());
        assertEquals("Carlos Gomez", result.getTeacherName());
    }
}