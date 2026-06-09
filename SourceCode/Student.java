package com.scheduleapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student {
    private final String studentId;
    private final String password;
    private final String name;
    private final List<Course> courses = new ArrayList<>();

    public Student(String studentId, String password, String name) {
        this.studentId = studentId;
        this.password = password;
        this.name = name;
    }

    public boolean signIn(String inputId, String inputPassword) {
        return studentId.equals(inputId) && password.equals(inputPassword);
    }

    public Course addCourse(String courseName) {
        Course course = new Course(courses.size() + 1, courseName);
        courses.add(course);
        return course;
    }

    public List<Course> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }
}
