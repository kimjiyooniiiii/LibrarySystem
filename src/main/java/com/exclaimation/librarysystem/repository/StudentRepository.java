package com.exclaimation.librarysystem.repository;

import com.exclaimation.librarysystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<Student> findByStudentId(String studentId);

    boolean existsByStudentIdAndPassword(String id, String encode);

    boolean existsByStudentId(String id);
}
