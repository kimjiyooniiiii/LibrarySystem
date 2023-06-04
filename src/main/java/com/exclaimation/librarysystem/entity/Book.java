package com.exclaimation.librarysystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long bookId;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String author;

    @Column(nullable = false)
    String publisher;

    @Column(nullable = false)
    String content;

    @Column(nullable = false)
    boolean isRent;

    @Column(nullable = false)
    boolean isReserve;

}
