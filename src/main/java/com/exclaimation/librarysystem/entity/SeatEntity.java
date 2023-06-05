package com.exclaimation.librarysystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name="seat")
public class SeatEntity {

    @Id    
    private Long seat_id;

    @Column
    private Long student_id;

    @Column
    private boolean enable;

    @Column
    private LocalDateTime start_time;

    @Column
    private LocalDateTime end_time;
}

