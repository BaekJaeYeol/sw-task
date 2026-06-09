package com.scheduleapp;

import java.util.Comparator;
import java.util.List;

public class CalendarManager {
    public void displayCalendar(List<Course> courses) {
        System.out.println("\n[캘린더 조회]");
        courses.stream()
                .flatMap(course -> course.getSchedules().stream()
                        .map(schedule -> new ScheduleView(course.getCourseName(), schedule)))
                .sorted(Comparator.comparing(view -> view.schedule().getDate()))
                .forEach(view -> System.out.println(view.schedule().toDisplayString(view.courseName())));
    }

    private record ScheduleView(String courseName, Schedule schedule) {
    }
}
