package com.scheduleapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Course {
    private final int courseId;
    private String courseName;
    private final List<Schedule> schedules = new ArrayList<>();

    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
    }

    public boolean deleteSchedule(int scheduleId) {
        return schedules.removeIf(schedule -> schedule.getScheduleId() == scheduleId);
    }

    public List<Schedule> getSchedules() {
        return Collections.unmodifiableList(schedules);
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
