package com.exclaimation.librarysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public class BookData {
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class response{
        public Long id;
        public String title;
        public String image;
        public String content;
        public String available;
    }
}
