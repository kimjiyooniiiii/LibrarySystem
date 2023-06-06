package com.exclaimation.librarysystem.controller;

import com.exclaimation.librarysystem.domain.Student;
import com.exclaimation.librarysystem.dto.JoinRequest;
import com.exclaimation.librarysystem.dto.LoginRequest;
import com.exclaimation.librarysystem.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody JoinRequest request){
        try{
            authService.join(request.getUserId(), request.getPassword());
            return ResponseEntity.ok("join success");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
