package com.exclaimation.librarysystem.controller;

import com.exclaimation.librarysystem.domain.RequireDto;
import com.exclaimation.librarysystem.dto.Auth;
import com.exclaimation.librarysystem.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String register() {
        return "/register";
    }

    @PostMapping("/register")
    public String register(HttpServletResponse response, Auth.RegisterRequest request) throws IOException {
        authService.register(response,request);
        return "redirect:/";

    }

    @GetMapping("/book/require")
    public String requireBook() {
        return "/wishForm";
    }

    @PostMapping("/book/require")
    public String requireBookPost(@AuthenticationPrincipal UserDetails userDetails, RequireDto require) {

        if(userDetails != null) {
            authService.requireBook(userDetails.getUsername(), require);
        }

        return "redirect:/";
    }

}
