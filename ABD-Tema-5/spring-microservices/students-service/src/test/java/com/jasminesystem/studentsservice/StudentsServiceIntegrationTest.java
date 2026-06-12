package com.jasminesystem.studentsservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        properties = {
                "eureka.client.enabled=false"
        }
)
@Transactional
public class StudentsServiceIntegrationTest {

    @Autowired
    private StudentController controller;

    @Test
    void testGetStudentsReal() {

        List<Student> students = controller.getStudents();

        assertNotNull(students);

        System.out.println("Cantidad de estudiantes: " + students.size());
    }

    @Test
    void testSaveStudentReal() {

        Student student = new Student(
                "Prueba Real",
                "prueba@gmail.com"
        );

        Student saved = controller.saveStudent(student);

        assertNotNull(saved);
        assertEquals("Prueba Real", saved.getName());
        assertEquals("prueba@gmail.com", saved.getInstitutionalEmail());

        System.out.println("Alumno guardado: " + saved.getName());
    }
}