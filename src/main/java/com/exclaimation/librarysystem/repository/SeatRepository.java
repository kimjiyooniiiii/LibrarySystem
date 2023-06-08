package com.exclaimation.librarysystem.repository;

import com.exclaimation.librarysystem.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<SeatEntity, Long> {

}
