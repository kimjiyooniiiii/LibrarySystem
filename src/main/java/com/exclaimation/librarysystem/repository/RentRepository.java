package com.exclaimation.librarysystem.repository;

import com.exclaimation.librarysystem.entity.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RentRepository extends JpaRepository<RentEntity, Long> {

    @Query("select r from rent r where r.book_id = :inputBookId and r.is_return = false")
    List<RentEntity> findByBookId(@Param("inputBookId") Long book_id);

    @Query("select r from rent r where r.student_id = :inputStudentId")
    List<RentEntity> findByStudentId(@Param("inputStudentId") String studentId);

    @Query("select r from rent r where r.rent_id = :rentId")
    Optional<RentEntity> findById(@Param("rentId") Long rentId);
}
