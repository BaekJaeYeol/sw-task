package com.scheduleapp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class NotificationService {
    public void showUpcomingNotifications(List<Course> courses) {
        LocalDate today = LocalDate.now();
        System.out.println("\n[마감 알림]");
        boolean found = false;
        for (Course course : courses) {
            for (Schedule schedule : course.getSchedules()) {
                long days = ChronoUnit.DAYS.between(today, schedule.getDate());
                if (schedule.getStatus() == Status.IN_PROGRESS && days >= 0 && days <= 3) {
                    found = true;
                    System.out.printf("- %s 과목의 %s '%s' 일정이 %d일 남았습니다.\n",
                            course.getCourseName(), schedule.getType(), schedule.getTitle(), days);
                }
            }
        }
        if (!found) {
            System.out.println("- 3일 이내 마감 일정이 없습니다.");
        }
    }
}
