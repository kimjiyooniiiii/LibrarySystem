package com.exclaimation.librarysystem.service;

import com.exclaimation.librarysystem.domain.Student;
import com.exclaimation.librarysystem.repository.StudentRepository;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final StudentRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(StudentRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Student> findUser(String studentId) {
        return repository.findByStudentId(studentId);
    }

    public boolean isValidMember(String id, String pw) {
        return repository.existsByStudentIdAndPassword(id, passwordEncoder.encode(pw));
    }

    public Student join(String id, String password) {
        Student student = Student.builder()
                .studentId(id)
                .password(passwordEncoder.encode(password))
                .build();

        if (isValidMember(student.getStudentId(), student.getPassword())) {
            return repository.save(student);
        } else {
            throw new DuplicateRequestException();
        }
    }

}
