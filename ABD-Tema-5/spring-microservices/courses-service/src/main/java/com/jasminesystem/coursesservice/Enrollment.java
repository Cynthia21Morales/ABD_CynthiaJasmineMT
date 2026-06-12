package com.jasminesystem.coursesservice;

import jakarta.persistence.*;

@Entity
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;

    private Long courseId;

    private String schedule;

    private String classroom;

    public Enrollment() {
    }

    public Enrollment(Long studentId, Long courseId, String schedule, String classroom) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.schedule = schedule;
        this.classroom = classroom;
    }

    public Long getId() {
        return id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}