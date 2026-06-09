# Personal Assignment and Exam Schedule Management System

## Student
- Student No: 22012135
- Name: 백재열

## Project Description
This Java Swing application helps students manage course-based assignment and exam schedules.

## Main Features
- Sign in
- Add course
- Add assignment schedule
- Add exam schedule
- View schedule list
- Set priority
- Receive deadline notification message
- Mark schedule as completed
- Delete schedule
- Save and load schedule data using CSV

## Login Information
- ID: 22012135
- Password: 1234

## How to Build
```bash
javac -encoding UTF-8 -d build/classes src/scheduleapp/*.java
jar cfe build/PersonalAssignmentScheduleSystem.jar scheduleapp.Main -C build/classes .
```

## How to Run
```bash
java -jar build/PersonalAssignmentScheduleSystem.jar
```
