package com.exclaimation.librarysystem.controller;

import com.exclaimation.librarysystem.dto.BookData;
import com.exclaimation.librarysystem.entity.Book;
import com.exclaimation.librarysystem.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public String search(Model model ,
                         @RequestParam("keyword") String keyword,
                         @RequestParam(value = "page", defaultValue="0") int page){

        model.addAttribute("books", bookService.search(keyword,page));
        model.addAttribute("keyword", keyword);
        model.addAttribute("maxPage", 5);
        model.addAttribute("userRole", "ADMIN");
        return "search";
    }
    @GetMapping("/content")
    public String getBookDetail(Model model, @RequestParam("bookId") long bookId){
        model.addAttribute("book", bookService.findBookDetail(bookId));
        model.addAttribute("userRole", "ADMIN");
        return "bookDetail";
    }

}
