# Student Schedule Manager

Java Swing application for managing university assignments and exam schedules.

## Features
- Sign in with demonstration credentials
- Manage courses, assignments, and exams
- View schedules and priorities
- Mark schedules complete or delete them
- Save and load data using CSV

## Build
```bash
javac -encoding UTF-8 -d build/classes src/scheduleapp/*.java
jar cfe build/PersonalAssignmentScheduleSystem.jar scheduleapp.Main -C build/classes .
```

## Run
```bash
java -jar build/PersonalAssignmentScheduleSystem.jar
```

## Security note
This is an educational project, not a production authentication system. Any example credentials in source code are for local demonstration only. Do not reuse real passwords.
