package com.exclaimation.librarysystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="seat")
public class SeatEntity {

    @Id    
    private Long seatId;

    @Column
    private Long userId;

    @Column
    private boolean isUsed;

}

