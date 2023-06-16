package com.exclaimation.librarysystem.service;

import com.exclaimation.librarysystem.dto.RequireDto;
import com.exclaimation.librarysystem.entity.Role;
import com.exclaimation.librarysystem.dto.Auth;
import com.exclaimation.librarysystem.entity.Require;
import com.exclaimation.librarysystem.entity.Student;
import com.exclaimation.librarysystem.repository.RequireRepository;
import com.exclaimation.librarysystem.repository.StudentRepository;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
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

    public boolean isValidMember(String id) {
        return !repository.existsByStudentId(id);
    }

    public Student register(HttpServletResponse response, Auth.RegisterRequest request) throws IOException {
        Student student = Student.builder()
                .studentId(request.getUserId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getUserName())
                .phone(request.getPhoneNumber())
                .userRole(Role.USER)
                .delay(false)
                .build();

        if (isValidMember(student.getStudentId())) {
            return repository.save(student);
        } else {
            response.setContentType("text/html; charset= UTF-8");
            response.setCharacterEncoding("UTF8");

            PrintWriter out = response.getWriter();
            out.println("<script>alert('중복된 계정입니다'); window.location.href = '/auth/register'</script> ");
            out.flush();
            throw new DuplicateRequestException();
        }
    }
}
