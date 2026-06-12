import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
  CommonModule,
  FormsModule,
  RouterOutlet,
   RouterLink
],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class AppComponent implements OnInit {

  apiTeachers = 'http://localhost:8080/teachers';
  apiStudents = 'http://localhost:8081/students';
  apiCourses = 'http://localhost:8082/courses';

  teachers: any[] = [];
  students: any[] = [];
  courses: any[] = [];

  teacherForm = {
    id: null as number | null,
    name: '',
    email: '',
    subject: ''
  };

  studentForm = {
  id: null as number | null,
  controlNumber: '',
  name: '',
  institutionalEmail: '',
  career: '',
  semester: null as number | null
};

  courseForm = {
  id: null as number | null,
  name: '',
  teacherId: null as number | null
};

editingCourse = false;

  editingTeacher = false;
  editingStudent = false;

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {


    this.loadTeachers();
    this.loadStudents();
    this.loadCourses();
  }

  // ======================
  // LOAD DATA
  // ======================

  loadTeachers() {
    this.http.get<any[]>(this.apiTeachers).subscribe({
      next: (data) => {
        this.teachers = data;
        this.cdr.detectChanges();
      },
      error: (err) => console.log("ERROR TEACHERS:", err)
    });
  }

  loadStudents() {
    this.http.get<any[]>(this.apiStudents).subscribe({
      next: (data) => {
        this.students = data;
        this.cdr.detectChanges();
      },
      error: (err) => console.log("ERROR STUDENTS:", err)
    });
  }

  loadCourses() {
    this.http.get<any[]>(this.apiCourses).subscribe({
      next: (data) => {
        this.courses = data;
        this.cdr.detectChanges();
      },
      error: (err) => console.log("ERROR COURSES:", err)
    });
  }

  // ======================
  // TEACHERS
  // ======================

  saveTeacher() {

    if (this.editingTeacher && this.teacherForm.id !== null) {
      this.http.put(`${this.apiTeachers}/${this.teacherForm.id}`, {
        name: this.teacherForm.name,
        email: this.teacherForm.email,
        subject: this.teacherForm.subject
      }).subscribe({
        next: () => {
          alert("Profesor actualizado");
          this.cancelEditTeacher();
          this.loadTeachers();
        }
      });
    } else {
      this.http.post(this.apiTeachers, {
        name: this.teacherForm.name,
        email: this.teacherForm.email,
        subject: this.teacherForm.subject
      }).subscribe({
        next: () => {
          alert("Profesor guardado");
          this.cancelEditTeacher();
          this.loadTeachers();
        }
      });
    }
  }

  editTeacher(teacher: any) {
    this.teacherForm = { ...teacher };
    this.editingTeacher = true;
  }

  deleteTeacher(id: number) {
  this.http.delete(`${this.apiTeachers}/${id}`, { responseType: 'text' })
    .subscribe({
      next: () => {
        alert("Profesor eliminado");
        this.loadTeachers();
      }
    });
}

  cancelEditTeacher() {
    this.teacherForm = {
      id: null,
      name: '',
      email: '',
      subject: ''
    };
    this.editingTeacher = false;
  }

  // ======================
  // STUDENTS
  // ======================

  saveStudent() {

  if (this.editingStudent && this.studentForm.id !== null) {

    this.http.put(
      `${this.apiStudents}/${this.studentForm.id}`,
      {
        controlNumber: this.studentForm.controlNumber,
        name: this.studentForm.name,
        institutionalEmail: this.studentForm.institutionalEmail,
        career: this.studentForm.career,
        semester: this.studentForm.semester
      }
    ).subscribe({
      next: () => {
        alert("Estudiante actualizado");
        this.cancelEditStudent();
        this.loadStudents();
      }
    });

  } else {

    this.http.post(
      this.apiStudents,
      {
        controlNumber: this.studentForm.controlNumber,
        name: this.studentForm.name,
        institutionalEmail: this.studentForm.institutionalEmail,
        career: this.studentForm.career,
        semester: this.studentForm.semester
      }
    ).subscribe({
      next: () => {
        alert("Estudiante guardado");
        this.cancelEditStudent();
        this.loadStudents();
      }
    });

  }
}

  editStudent(student: any) {
    this.studentForm = { ...student };
    this.editingStudent = true;
  }

  deleteStudent(id: number) {
  this.http.delete(`${this.apiStudents}/${id}`, { responseType: 'text' })
    .subscribe({
      next: () => {
        alert("Estudiante eliminado");
        this.loadStudents();
      }
    });
}

  cancelEditStudent() {
  this.studentForm = {
    id: null,
    controlNumber: '',
    name: '',
    institutionalEmail: '',
    career: '',
    semester: null
  };

  this.editingStudent = false;
}

  // ======================
  // CURSOS
  // ======================
  
 deleteCourse(id: number) {
  this.http.delete(`${this.apiCourses}/${id}`, { responseType: 'text' })
    .subscribe({
      next: () => {
        alert("Curso eliminado");
        this.loadCourses();
      }
    });
}

saveCourse() {

  if (this.editingCourse && this.courseForm.id !== null) {

    this.http.put(
      `${this.apiCourses}/${this.courseForm.id}`,
      {
        name: this.courseForm.name,
        teacherId: this.courseForm.teacherId
      }
    ).subscribe({
      next: () => {
        alert("Curso actualizado");
        this.cancelEditCourse();
        this.loadCourses();
      }
    });

  } else {

    this.http.post(
      this.apiCourses,
      {
        name: this.courseForm.name,
        teacherId: this.courseForm.teacherId
      }
    ).subscribe({
      next: () => {
        alert("Curso guardado");
        this.cancelEditCourse();
        this.loadCourses();
      }
    });

  }
}

editCourse(course: any) {

  this.courseForm = {
    ...course
  };

  this.editingCourse = true;
}

cancelEditCourse() {

  this.courseForm = {
    id: null,
    name: '',
    teacherId: null
  };

  this.editingCourse = false;
}

  getTeacherName(teacherId: number): string {
    const teacher = this.teachers.find(t => t.id === teacherId);
    return teacher ? teacher.name : 'No encontrado';
  }

showSupport() {

  alert(`
SOPORTE TÉCNICO

Instituto Tecnológico de Frontera Comalapa

Correo:
soporte@itfc.edu.mx

Horario:
Lunes a Viernes
08:00 AM - 05:00 PM
`);

}

showAbout() {

  alert(`
SISTEMA INTEGRAL DE GESTIÓN ACADÉMICA

Versión 1.0

Desarrollado para la administración de:

• Estudiantes
• Profesores
• Cursos

Instituto Tecnológico de Frontera Comalapa
`);

}

}