package com.exclaimation.librarysystem.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    private String studentId;
    private String name;
    private String password;
    private String major;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDt;
    private String phone;
    private Boolean delay;
    @Enumerated(EnumType.STRING)
    private Role userRole;

    public static Student createUser(String userId, String pw, PasswordEncoder passwordEncoder) {
        return Student
                .builder()
                .studentId(userId)
                .password(passwordEncoder.encode(pw))
                .userRole(Role.USER)
                .build();

    }

}
