package com.scheduleapp;

import java.time.LocalDate;

public class Assignment extends Schedule {
    public Assignment(int scheduleId, String assignmentName, LocalDate dueDate, Priority priority) {
        super(scheduleId, assignmentName, dueDate, priority);
    }

    @Override
    public String getType() {
        return "Assignment";
    }
}
