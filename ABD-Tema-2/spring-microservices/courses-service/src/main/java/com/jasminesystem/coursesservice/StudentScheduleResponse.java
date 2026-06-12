package com.jasminesystem.coursesservice;

public class StudentScheduleResponse {

    private String studentName;
    private String courseName;
    private String teacherName;
    private String schedule;
    private String classroom;

    public StudentScheduleResponse(
            String studentName,
            String courseName,
            String teacherName,
            String schedule,
            String classroom
    ) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.schedule = schedule;
        this.classroom = classroom;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getClassroom() {
        return classroom;
    }
}