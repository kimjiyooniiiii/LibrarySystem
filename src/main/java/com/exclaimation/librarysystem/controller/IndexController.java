package com.exclaimation.librarysystem.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class IndexController {

    @GetMapping("")
    public String index(@AuthenticationPrincipal User userDetails, Model model) {

        if(userDetails != null) {
            model.addAttribute("loginId", userDetails.getUsername());
            model.addAttribute("loginRoles", userDetails.getAuthorities());
            List<GrantedAuthority> str = userDetails.getAuthorities().stream().toList();
            if(str.get(0).getAuthority().equals("ROLE_ADMIN")){
                return "/admin/adminIndex";
            }
        }
        return "/index";
    }
}
