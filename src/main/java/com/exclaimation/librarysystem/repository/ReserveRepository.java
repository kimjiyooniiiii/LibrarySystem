package com.exclaimation.librarysystem.repository;

import com.exclaimation.librarysystem.entity.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReserveRepository extends JpaRepository<ReserveEntity, Long> {

    @Query("select r from reserve r where r.book_id = :inputBookId")
    List<ReserveEntity> findByBookId(@Param("inputBookId") Long book_id);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END FROM reserve r WHERE r.book_id = :bookId AND r.student_id = :studentId")
    boolean existsByBookIdAndStudentId(@Param("bookId") Long bookId, @Param("studentId") String studentId);

    @Query("select count(r) from reserve r where r.book_id = :bookId")
    Long countBookId(@Param("bookId") Long bookId);

}
