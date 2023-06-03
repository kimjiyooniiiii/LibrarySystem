package com.exclaimation.librarysystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    @GetMapping("/books/wishForm")
    public String wishBook() {
        return "wishForm";
    }
}
