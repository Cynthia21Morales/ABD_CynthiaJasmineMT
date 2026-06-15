package com.jasminesystem.studentsservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    private StudentRepository repository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private StudentController controller;

    @Test
    void testGetStudents() {

        List<Student> students = Arrays.asList(
                new Student("Jasmine Morales", "jasmine@gmail.com"),
                new Student("Luis Daniel", "luis@gmail.com")
        );

        when(repository.findAll()).thenReturn(students);

        List<Student> result = controller.getStudents();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testSaveStudent() {

        Student student = new Student("Kevin Morales", "kevin@gmail.com");

        when(repository.save(student)).thenReturn(student);

        Student result = controller.saveStudent(student);

        assertEquals("Kevin Morales", result.getName());
        assertEquals("kevin@gmail.com", result.getInstitutionalEmail());
        verify(repository, times(1)).save(student);
    }

    @Test
    void testGetCoursesFromService() {

        when(restTemplate.getForObject(
                "http://COURSES-SERVICE/courses",
                String.class
        )).thenReturn("Lista de cursos");

        String result = controller.getCoursesFromService();

        assertEquals("Lista de cursos", result);

        verify(restTemplate, times(1)).getForObject(
                "http://COURSES-SERVICE/courses",
                String.class
        );
    }

    @Test
    void testGetStudentsEmptyList() {

        when(repository.findAll()).thenReturn(List.of());

        List<Student> result = controller.getStudents();

        assertEquals(0, result.size());

        verify(repository, times(1)).findAll();
    }
}