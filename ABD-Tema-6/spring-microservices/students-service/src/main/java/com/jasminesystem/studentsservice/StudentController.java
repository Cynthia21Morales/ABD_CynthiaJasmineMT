package com.jasminesystem.studentsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class StudentController {

    private static final Logger log =
            LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentRepository repository;

    @Autowired
    private RestTemplate restTemplate;


    // ==========================
    // OBTENER TODOS LOS ALUMNOS
    // ==========================
    @GetMapping("/students")
    public List<Student> getStudents() {

        log.info("Se está obteniendo la lista de estudiantes");

        return repository.findAll();
    }


    // ==========================
    // GUARDAR ALUMNO
    // ==========================
    @PostMapping("/students")
    public Student saveStudent(@RequestBody Student student) {

        log.info("Guardando estudiante: {}", student.getName());

        Student saved = repository.save(student);

        log.info("Estudiante guardado correctamente con ID: " + saved.getId());

        return saved;
    }


    // ==========================
    // ELIMINAR ALUMNO
    // ==========================
    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {

        log.info("Eliminando estudiante con ID: {}", id);

        repository.deleteById(id);

        log.info("Estudiante eliminado correctamente");

        return "Estudiante eliminado correctamente";
    }


    // ==========================
    // EDITAR ALUMNO
    // ==========================
    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable Long id,
                                 @RequestBody Student student) {

        log.info("Actualizando estudiante con ID: " + id);

        Student existing = repository.findById(id).orElseThrow();

        existing.setControlNumber(student.getControlNumber());
        existing.setName(student.getName());
        existing.setInstitutionalEmail(student.getInstitutionalEmail());
        existing.setCareer(student.getCareer());
        existing.setSemester(student.getSemester());

        Student updated = repository.save(existing);

        log.info("Estudiante actualizado correctamente");

        return updated;
    }

    // ==========================
    // CONSULTAR CURSOS
    // ==========================
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