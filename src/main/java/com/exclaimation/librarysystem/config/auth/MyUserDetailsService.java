package com.exclaimation.librarysystem.config.auth;

import com.exclaimation.librarysystem.entity.Student;
import com.exclaimation.librarysystem.service.AuthService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {
    private final AuthService authService;

    public MyUserDetailsService(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> findOne = authService.findUser(username);
        Student student = findOne.orElseThrow(() -> new UsernameNotFoundException("없는 계정입니다."));
        return User.builder()
                .username(student.getStudentId())
                .password(student.getPassword())
                .roles(student.getUserRole().toString())
                .build();
    }
}
