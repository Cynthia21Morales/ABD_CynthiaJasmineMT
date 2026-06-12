package com.jasminesystem.coursesservice;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TeacherController {

    private final TeacherRepository repository;

    public TeacherController(TeacherRepository repository) {
        this.repository = repository;
    }

    // OBTENER TODOS
    @GetMapping("/teachers")
    public List<Teacher> getTeachers() {
        return repository.findAll();
    }

    // GUARDAR
    @PostMapping("/teachers")
    public Teacher saveTeacher(@RequestBody Teacher teacher) {
        return repository.save(teacher);
    }

    // EDITAR
    @PutMapping("/teachers/{id}")
    public Teacher updateTeacher(@PathVariable Long id,
                                 @RequestBody Teacher teacher) {

        Teacher existing = repository.findById(id).orElseThrow();

        existing.setName(teacher.getName());
        existing.setEmail(teacher.getEmail());
        existing.setSubject(teacher.getSubject());

        return repository.save(existing);
    }

    // ELIMINAR
    @DeleteMapping("/teachers/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        repository.deleteById(id);
        return "Profesor eliminado correctamente";
    }
}