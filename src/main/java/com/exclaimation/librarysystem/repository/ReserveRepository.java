package com.exclaimation.librarysystem.repository;

import com.exclaimation.librarysystem.entity.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReserveRepository extends JpaRepository<ReserveEntity, Long> {

    @Query("select r from reserve r where r.book_id = :inputBookId")
    List<ReserveEntity> findByBookId(@Param("inputBookId") Long book_id);

    @Query("select r from reserve r where r.book_id = :bookId and r.student_id = :studentId")
    Optional<ReserveEntity> findByBookIdAndStudentId(@Param("bookId")Long book_id, @Param("studentId")String studentId);

}
