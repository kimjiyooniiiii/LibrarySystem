package com.exclaimation.librarysystem.service;

import com.exclaimation.librarysystem.dto.RequireDto;
import com.exclaimation.librarysystem.entity.Require;
import com.exclaimation.librarysystem.entity.Student;
import com.exclaimation.librarysystem.repository.RequireRepository;
import com.exclaimation.librarysystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class RequireService {

    private final StudentRepository repository;
    private final RequireRepository requireRepository;

    public RequireService(StudentRepository repository, RequireRepository requireRepository) {
        this.repository = repository;
        this.requireRepository = requireRepository;
    }

    // 희망 도서 신청
    public void requireBook(String userId, RequireDto require) {
        Student student = repository.findByStudentId(userId).orElseThrow();

        Require newRequire = Require.builder()
                .student(student)
                .title(require.getTitle())
                .author(require.getAuthor())
                .phoneNumber(require.getPhoneNumber())
                .publisher(require.getPublisher())
                .year(require.getYear())
                .build();

        requireRepository.save(newRequire);
    }
}
