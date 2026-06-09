package com.scheduleapp;

public enum Status {
    IN_PROGRESS("진행 중"),
    COMPLETED("완료");

    private final String label;

    Status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
