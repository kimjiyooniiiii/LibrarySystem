package com.exclaimation.librarysystem.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

public class Seat {

    @Data
    public static class Simple{
        private Long seat_id;
        private Long student_id;
        private boolean enable;
        private LocalDateTime start_time;
        private LocalDateTime end_time;
    }
}
