package com.scheduleapp;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final Student student;
    private final CalendarManager calendarManager = new CalendarManager();
    private final NotificationService notificationService = new NotificationService();
    private final ScheduleRepository repository = new ScheduleRepository(Path.of("data", "schedules.tsv"));
    private final Scanner scanner = new Scanner(System.in);
    private int nextScheduleId = 4;

    public ConsoleUI(Student student) {
        this.student = student;
    }

    public void start() {
        repository.loadSampleData(student);
        System.out.println("Personal Assignment and Exam Schedule Management System");
        if (!login()) {
            System.out.println("로그인 실패 횟수가 초과되어 프로그램을 종료합니다.");
            return;
        }
        menuLoop();
    }

    private boolean login() {
        for (int attempt = 0; attempt < 3; attempt++) {
            System.out.print("ID 입력: ");
            String id = scanner.nextLine();
            System.out.print("Password 입력: ");
            String password = scanner.nextLine();
            if (student.signIn(id, password)) {
                System.out.println(student.getName() + "님 로그인 성공");
                return true;
            }
            System.out.println("로그인 실패");
        }
        return false;
    }

    private void menuLoop() {
        while (true) {
            printMenu();
            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1" -> addCourse();
                    case "2" -> addSchedule(true);
                    case "3" -> addSchedule(false);
                    case "4" -> calendarManager.displayCalendar(student.getCourses());
                    case "5" -> markCompleted();
                    case "6" -> notificationService.showUpcomingNotifications(student.getCourses());
                    case "7" -> saveData();
                    case "0" -> {
                        saveData();
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    }
                    default -> System.out.println("잘못된 메뉴입니다.");
                }
            } catch (Exception e) {
                System.out.println("오류: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("\n===== Main Dashboard =====");
        System.out.println("1. 과목 등록");
        System.out.println("2. 과제 일정 등록");
        System.out.println("3. 시험 일정 등록");
        System.out.println("4. 캘린더 조회");
        System.out.println("5. 완료 처리");
        System.out.println("6. 알림 확인");
        System.out.println("7. 데이터 저장");
        System.out.println("0. 종료");
        System.out.print("메뉴 선택: ");
    }

    private void addCourse() {
        System.out.print("과목명 입력: ");
        String courseName = scanner.nextLine();
        Course course = student.addCourse(courseName);
        System.out.println("과목 등록 완료: " + course.getCourseName());
    }

    private void addSchedule(boolean assignment) {
        Course course = selectCourse();
        System.out.print("일정명 입력: ");
        String title = scanner.nextLine();
        System.out.print("날짜 입력(YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.print("우선순위 선택(1:높음, 2:보통, 3:낮음): ");
        Priority priority = Priority.fromNumber(Integer.parseInt(scanner.nextLine()));

        Schedule schedule = assignment
                ? new Assignment(nextScheduleId++, title, date, priority)
                : new Exam(nextScheduleId++, title, date, priority);
        course.addSchedule(schedule);
        System.out.println("일정 등록 완료");
    }

    private Course selectCourse() {
        List<Course> courses = student.getCourses();
        if (courses.isEmpty()) {
            throw new IllegalStateException("등록된 과목이 없습니다.");
        }
        for (Course course : courses) {
            System.out.printf("%d. %s\n", course.getCourseId(), course.getCourseName());
        }
        System.out.print("과목 선택: ");
        int id = Integer.parseInt(scanner.nextLine());
        return courses.stream()
                .filter(course -> course.getCourseId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과목입니다."));
    }

    private void markCompleted() {
        calendarManager.displayCalendar(student.getCourses());
        System.out.print("완료 처리할 schedule ID 입력: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (Course course : student.getCourses()) {
            for (Schedule schedule : course.getSchedules()) {
                if (schedule.getScheduleId() == id) {
                    schedule.markCompleted();
                    System.out.println("완료 처리되었습니다.");
                    return;
                }
            }
        }
        System.out.println("해당 일정이 없습니다.");
    }

    private void saveData() throws IOException {
        repository.save(student.getCourses());
        System.out.println("data/schedules.tsv 파일에 저장되었습니다.");
    }
}
