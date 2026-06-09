package com.scheduleapp;

import java.time.LocalDate;

public class Exam extends Schedule {
    public Exam(int scheduleId, String examName, LocalDate examDate, Priority priority) {
        super(scheduleId, examName, examDate, priority);
    }

    @Override
    public String getType() {
        return "Exam";
    }
}
