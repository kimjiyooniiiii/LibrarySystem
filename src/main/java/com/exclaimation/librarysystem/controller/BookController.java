package com.exclaimation.librarysystem.controller;

import com.exclaimation.librarysystem.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/wishForm")
    public String wishBook() {
        return "wishForm";
    }

    @PostMapping("/search")
    public String search(Model model ,
                         @RequestParam("keyword") String keyword,
                         @RequestParam(value = "pageNumber", defaultValue="0") Integer page){

        model.addAttribute("books", bookService.search(keyword,page));
        model.addAttribute("keyword", keyword);
        return "search";
    }

}
