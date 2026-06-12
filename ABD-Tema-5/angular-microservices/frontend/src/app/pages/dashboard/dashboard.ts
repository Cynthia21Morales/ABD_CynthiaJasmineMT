import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RouterLink } from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css']
})
export class Dashboard implements OnInit {

  totalStudents = 0;
totalTeachers = 0;
totalCourses = 0;

  constructor(
  private http: HttpClient,
  private cdr: ChangeDetectorRef
) {}

  ngOnInit(): void {

    console.log('DASHBOARD CARGADO');


this.http.get<any[]>('http://localhost:8081/students')
.subscribe(data => {
  this.totalStudents = data.length;
  this.cdr.detectChanges();
  console.log('Students:', this.totalStudents);
});

this.http.get<any[]>('http://localhost:8080/teachers')
.subscribe(data => {
  this.totalTeachers = data.length;
  this.cdr.detectChanges();
});

this.http.get<any[]>('http://localhost:8082/courses')
.subscribe(data => {
  this.totalCourses = data.length;
  this.cdr.detectChanges();
});
}
}