package com.scheduleapp;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("22012135", "1234", "백재열");
        ConsoleUI ui = new ConsoleUI(student);
        ui.start();
    }
}
