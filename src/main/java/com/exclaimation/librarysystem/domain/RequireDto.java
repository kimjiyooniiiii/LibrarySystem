package com.exclaimation.librarysystem.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequireDto {

    String title;
    String author;
    String publisher;
    String year;
    String phoneNumber;
}
