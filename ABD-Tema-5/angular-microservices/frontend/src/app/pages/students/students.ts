import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-students',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './students.html',
  styleUrl: './students.css'
})
export class Students implements OnInit {

  apiStudents = 'http://localhost:8081/students';

  students: any[] = [];

  studentForm = {
    id: null as number | null,
    controlNumber: '',
    name: '',
    institutionalEmail: '',
    career: '',
    semester: null as number | null
  };

  editingStudent = false;

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadStudents();
  }

  loadStudents() {
    this.http.get<any[]>(this.apiStudents).subscribe({
      next: data => {
        this.students = data;
        this.cdr.detectChanges();
      }
    });
  }

  saveStudent() {

    if (this.editingStudent && this.studentForm.id !== null) {

      this.http.put(
        `${this.apiStudents}/${this.studentForm.id}`,
        this.studentForm
      ).subscribe(() => {
        this.cancelEditStudent();
        this.loadStudents();
      });

    } else {

      this.http.post(
        this.apiStudents,
        this.studentForm
      ).subscribe(() => {
        this.cancelEditStudent();
        this.loadStudents();
      });

    }
  }

  editStudent(student: any) {
    this.studentForm = { ...student };
    this.editingStudent = true;
  }

  deleteStudent(id: number) {
    this.http.delete(
      `${this.apiStudents}/${id}`,
      { responseType: 'text' }
    ).subscribe(() => {
      this.loadStudents();
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

}