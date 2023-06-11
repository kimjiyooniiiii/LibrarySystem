package com.exclaimation.librarysystem.controller;

import com.exclaimation.librarysystem.dto.RequireDto;
import com.exclaimation.librarysystem.service.RequireService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequireController {

    private final RequireService requireService;

    public RequireController(RequireService requireService) {
        this.requireService = requireService;
    }

    @GetMapping("/require")
    public String requireBook() {
        return "/wishForm";
    }

    // 희망 도서 신청
    @PostMapping("/require")
    public String requireBookPost(@AuthenticationPrincipal UserDetails userDetails, RequireDto require) {

        if(userDetails != null) {
            requireService.requireBook(userDetails.getUsername(), require);
        }

        return "redirect:/";
    }
}
