package com.exclaimation.librarysystem.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class IndexController {

    @GetMapping("")
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        if(userDetails != null) {
            model.addAttribute("loginId", userDetails.getUsername());
            model.addAttribute("loginRoles", userDetails.getAuthorities());
        }
        return "/index";
    }
}
