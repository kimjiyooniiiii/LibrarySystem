package com.exclaimation.librarysystem.domain;

import lombok.Data;

public class Seat {

    @Data
    public static class Create{
        private Long seatId;
        private Long userId;
        private boolean isUsed;
    }
}
