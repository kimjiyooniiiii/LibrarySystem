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
public class Require {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long requireId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;

    String title;

    String author;

    String publisher;

    String year;

    String phoneNumber;

    @CreationTimestamp
    LocalDate createdDt;
}
