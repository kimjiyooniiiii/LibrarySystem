package com.exclaimation.librarysystem.dto;

import lombok.*;

public class BookData {
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class response{
        private Long id;
        private String title;
        private String image;
        private String content;
        private String author;
    }

    @Getter
    @Setter
    public static class rentRequest{
        private Long bookId;
        private String studentId;
    }
}
