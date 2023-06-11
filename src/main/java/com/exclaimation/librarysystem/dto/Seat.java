package com.exclaimation.librarysystem.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

public class Seat {

    @Data
    public static class Simple{
        private Long seat_id;
        private String student_id;
        private boolean enable;
        private LocalDateTime start_time;
        private LocalDateTime end_time;
    }
}
