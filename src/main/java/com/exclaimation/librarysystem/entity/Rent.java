package com.exclaimation.librarysystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Rent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long rentId;

    @ManyToOne
    @JoinColumn(name = "studentId")
    Student student;

    @ManyToOne
    @JoinColumn(name = "bookId")
    Book book;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDate rentDt;

    @Column
    LocalDate returnDt;

    @Column
    Long spareDt;

    @Column(nullable = false)
    boolean isReturn;

    @Column
    boolean isContinue;

}
