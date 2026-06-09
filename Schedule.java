package com.scheduleapp;

import java.time.LocalDate;

public abstract class Schedule {
    private final int scheduleId;
    private String title;
    private LocalDate date;
    private Status status;
    private Priority priority;

    protected Schedule(int scheduleId, String title, LocalDate date, Priority priority) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.date = date;
        this.priority = priority;
        this.status = Status.IN_PROGRESS;
    }

    public abstract String getType();

    public void updateSchedule(String title, LocalDate date, Priority priority) {
        this.title = title;
        this.date = date;
        this.priority = priority;
    }

    public void markCompleted() {
        this.status = Status.COMPLETED;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public Status getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public String toStorageLine(int courseId) {
        return String.join("\t",
                String.valueOf(courseId),
                String.valueOf(scheduleId),
                getType(),
                title,
                date.toString(),
                priority.name(),
                status.name());
    }

    public String toDisplayString(String courseName) {
        return String.format("[%d] %s | %s | %s | %s | %s",
                scheduleId, courseName, getType(), title, date, priority.getLabel() + "/" + status.getLabel());
    }
}
