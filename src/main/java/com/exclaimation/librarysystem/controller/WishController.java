package com.exclaimation.librarysystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WishController {
    @GetMapping("/wish")
    public String wish() {
        return "wish";
    }
}
