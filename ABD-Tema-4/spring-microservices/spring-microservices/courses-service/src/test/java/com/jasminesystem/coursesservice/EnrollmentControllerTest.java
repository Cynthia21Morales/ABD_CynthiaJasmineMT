package com.jasminesystem.coursesservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class EnrollmentControllerTest {

    @Mock
    private EnrollmentRepository repository;

    @InjectMocks
    private EnrollmentController controller;

    @Test
    void testCreateEnrollment() {

        Enrollment enrollment = new Enrollment(
                1L,
                2L,
                "7:00 AM",
                "A-101"
        );

        when(repository.save(enrollment)).thenReturn(enrollment);

        Enrollment result = controller.createEnrollment(enrollment);

        assertEquals("7:00 AM", result.getSchedule());
        assertEquals("A-101", result.getClassroom());

        verify(repository, times(1)).save(enrollment);
    }
}