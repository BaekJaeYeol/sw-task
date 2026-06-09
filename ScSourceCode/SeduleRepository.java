package com.scheduleapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class ScheduleRepository {
    private final Path dataFile;

    public ScheduleRepository(Path dataFile) {
        this.dataFile = dataFile;
    }

    public void save(List<Course> courses) throws IOException {
        Files.createDirectories(dataFile.getParent());
        StringBuilder builder = new StringBuilder();
        for (Course course : courses) {
            for (Schedule schedule : course.getSchedules()) {
                builder.append(schedule.toStorageLine(course.getCourseId())).append(System.lineSeparator());
            }
        }
        Files.writeString(dataFile, builder.toString());
    }

    public void loadSampleData(Student student) {
        Course softwareEngineering = student.addCourse("Software Engineering");
        Course database = student.addCourse("Database");

        softwareEngineering.addSchedule(new Assignment(1, "Design Document 제출", LocalDate.now().plusDays(2), Priority.HIGH));
        softwareEngineering.addSchedule(new Exam(2, "기말고사", LocalDate.now().plusDays(10), Priority.HIGH));
        database.addSchedule(new Assignment(3, "SQL Practice", LocalDate.now().plusDays(5), Priority.MEDIUM));
    }
}
