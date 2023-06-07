package com.exclaimation.librarysystem.controller;

import com.exclaimation.librarysystem.dto.BookData;
import com.exclaimation.librarysystem.service.BookService;
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

    @GetMapping("/wishForm")
    public String wishBook() {
        return "wishForm";
    }

        @PostMapping("/search")
    public String search(Model model ,
                         @RequestParam("keyword") String keyword,
                         @PageableDefault(page=0, size=10, sort="id", direction= Sort.Direction.DESC) Pageable pageable){
        int max = bookService.getMaxNumber(keyword, pageable);
        List<BookData.response> list = bookService.search(keyword,pageable, max);

        model.addAttribute("books", list);
        model.addAttribute("keyword", keyword);

        int nowPage = pageable.getPageNumber() +1;
        int startPage = Math.max(nowPage -4, 1);
        int endPage =Math.min(nowPage+9, list.size());
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "search";
    }
//    @PostMapping("/search")
//    public String search(Model model,
//                         @RequestParam("keyword") String keyword,
//                         @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//
//
//        return "search";
//    }
//    @PostMapping("/search")
//    public String search(Model model ,
//                         @RequestParam("keyword") String keyword,
//                         @RequestParam(value = "pageNumber", defaultValue="0") Integer page){
//
//        model.addAttribute("books", bookService.search(keyword,page));
//        model.addAttribute("keyword", keyword);
//        return "search";
//    }

}
