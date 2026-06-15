import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-courses',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './courses.html',
  styleUrl: './courses.css'
})
export class Courses implements OnInit {

apiCourses = 'http://jasminesystems.mx/courses';
apiTeachers = 'http://jasminesystems.mx/teachers';

  courses: any[] = [];
  teachers: any[] = [];

  courseForm = {
    id: null as number | null,
    name: '',
    teacherId: null as number | null
  };

  editingCourse = false;

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadCourses();
    this.loadTeachers();
  }

  loadCourses() {
    this.http.get<any[]>(this.apiCourses).subscribe({
      next: data => {
        console.log('COURSES', data);
        this.courses = data;
        this.cdr.detectChanges();
      }
    });
  }

  loadTeachers() {
    this.http.get<any[]>(this.apiTeachers).subscribe({
      next: data => {
        this.teachers = data;
      }
    });
  }

  saveCourse() {

    if (this.editingCourse && this.courseForm.id !== null) {

      this.http.put(
        `${this.apiCourses}/${this.courseForm.id}`,
        this.courseForm
      ).subscribe(() => {
        this.cancelEditCourse();
        this.loadCourses();
      });

    } else {

      this.http.post(
        this.apiCourses,
        this.courseForm
      ).subscribe(() => {
        this.cancelEditCourse();
        this.loadCourses();
      });

    }
  }

  editCourse(course: any) {
    this.courseForm = { ...course };
    this.editingCourse = true;
  }

  deleteCourse(id: number) {
    this.http.delete(
      `${this.apiCourses}/${id}`,
      { responseType: 'text' }
    ).subscribe(() => {
      this.loadCourses();
    });
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
    const teacher = this.teachers.find(
      t => t.id === teacherId
    );

    return teacher
      ? teacher.name
      : 'No encontrado';
  }

}