import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-teachers',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './teachers.html',
  styleUrl: './teachers.css'
})
export class Teachers implements OnInit {

  apiTeachers = 'http://localhost:8080/teachers';

  teachers: any[] = [];

  teacherForm = {
    id: null as number | null,
    name: '',
    email: '',
    subject: ''
  };

  editingTeacher = false;

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadTeachers();
  }

  loadTeachers() {
    this.http.get<any[]>(this.apiTeachers).subscribe({
      next: data => {
        this.teachers = data;
        this.cdr.detectChanges();
      }
    });
  }

  saveTeacher() {

    if (this.editingTeacher && this.teacherForm.id !== null) {

      this.http.put(
        `${this.apiTeachers}/${this.teacherForm.id}`,
        this.teacherForm
      ).subscribe(() => {
        this.cancelEditTeacher();
        this.loadTeachers();
      });

    } else {

      this.http.post(
        this.apiTeachers,
        this.teacherForm
      ).subscribe(() => {
        this.cancelEditTeacher();
        this.loadTeachers();
      });

    }
  }

  editTeacher(teacher: any) {
    this.teacherForm = { ...teacher };
    this.editingTeacher = true;
  }

  deleteTeacher(id: number) {
    this.http.delete(
      `${this.apiTeachers}/${id}`,
      { responseType: 'text' }
    ).subscribe(() => {
      this.loadTeachers();
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

}