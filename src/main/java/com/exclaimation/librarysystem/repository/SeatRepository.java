package com.exclaimation.librarysystem.repository;

import com.exclaimation.librarysystem.dto.Seat;
import com.exclaimation.librarysystem.entity.RentEntity;
import com.exclaimation.librarysystem.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<SeatEntity, Long> {

    @Query("select s from seat s where s.student_id = :studentId")
     List<SeatEntity> findByStudentId(@Param("studentId") String studentId);

}
