package com.exclaimation.librarysystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity(name="reserve")
public class ReserveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservation_id;

    @Column
    private Long book_id;

    @Column
    private String book_name;

    @Column
    private String student_id;

    @Column
    private LocalDate reservation_date;

}
