package com.exclaimation.librarysystem.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity(name="rent")
public class RentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rent_id;

    @Column
    private Long book_id;

    @Column
    private String student_id;

    @Column
    private String book_name;

    @Column
    private LocalDate rent_dt;

    @Column
    private LocalDate return_dt;

    @Column
    private Long spare_dt;

    @Column
    private boolean is_return; // 반납여부

    @Column
    private boolean is_continue; //  연장여부?
}
