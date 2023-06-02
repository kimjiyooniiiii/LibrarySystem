package com.exclaimation.librarysystem.repository;

import com.exclaimation.librarysystem.entity.SeatEntity;

import java.util.List;
import java.util.Optional;

public interface SeatRepository {
    void save(SeatEntity seatEntity);

    List<SeatEntity> findAll();

    Optional<SeatEntity> findById(Long seatId);
}
