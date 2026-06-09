package com.scheduleapp;

public enum Priority {
    HIGH("높음"),
    MEDIUM("보통"),
    LOW("낮음");

    private final String label;

    Priority(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Priority fromNumber(int number) {
        return switch (number) {
            case 1 -> HIGH;
            case 3 -> LOW;
            default -> MEDIUM;
        };
    }
}
