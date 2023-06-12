package com.exclaimation.librarysystem.controller;

import com.exclaimation.librarysystem.entity.ReserveEntity;
import com.exclaimation.librarysystem.service.BookService;
import com.exclaimation.librarysystem.service.RentService;
import com.exclaimation.librarysystem.service.ReserveService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final ReserveService reserveService;
    private final RentService rentService;

    public BookController(BookService bookService, ReserveService reserveService, RentService rentService) {
        this.bookService = bookService;
        this.reserveService = reserveService;
        this.rentService = rentService;
    }

    // 도서 검색
    @GetMapping("/search")
    public String search(Model model ,
                         @RequestParam("keyword") String keyword,
                         @RequestParam(value = "page", defaultValue="0") int page,
                         @AuthenticationPrincipal UserDetails userDetails){

        model.addAttribute("books", bookService.search(keyword,page));
        model.addAttribute("keyword", keyword);
        model.addAttribute("maxPage", 5);
        model.addAttribute("userRole", "ADMIN");
        if(userDetails != null) {
            model.addAttribute("loginId", userDetails.getUsername());
            model.addAttribute("loginRoles", userDetails.getAuthorities());
        }
        return "search";
    }

    // 도서 상세 페이지
    @GetMapping("/content")
    public String getBookDetail(Model model,
                                @RequestParam("bookId") long bookId,
                                @AuthenticationPrincipal UserDetails userDetails){
        model.addAttribute("book", bookService.findBookDetail(bookId));
        model.addAttribute("userRole", "ADMIN");
        if(userDetails != null) {
            model.addAttribute("loginId", userDetails.getUsername());
            model.addAttribute("loginRoles", userDetails.getAuthorities());
        }
        return "bookDetail";
    }

    // 대출
//    @PostMapping("/rent")
//    public String rent(
//            @RequestParam(value = "book_id") Long book_id)  throws IllegalAccessException{
//
//        // 예약자 중 가장 빠른 사람에게 대출
//        Long r_id = reserveService.fastReservationIdByBookId(book_id, out);
//        if (r_id == 0) {
//            System.out.println("예약자가 없습니다");
//        } else {
//            ReserveEntity reserveEntity = reserveService.findById(r_id);
//            int result = rentService.rent(reserveEntity.getBook_id(), reserveEntity.getStudent_id());
//
//            if (result != 0) {
//                System.out.println("대출 insert 에러발생");
//                return "rent";
//            }
//
//            result = reserveService.deleteById(r_id);
//            if (result != 0) {
//                System.out.println("예약 delete 에러 발생");
//                return "rent";
//            }
//        }
//
//        return "redirect:/";
//    }

    //반납
    @PostMapping("/return")
    public void returnBook(
            HttpServletResponse response,
            @RequestParam(value = "rent_id") Long rent_id) throws IOException {
        response.setContentType("text/html; charset= UTF-8");
        response.setCharacterEncoding("UTF8");

        PrintWriter out = response.getWriter();
        rentService.returnRent(rent_id, out);
    }

    //반납
    @PostMapping("/return/bookId")
    public void returnBookId(
            HttpServletResponse response,
            @RequestParam(value = "book_id") Long rent_id) throws IOException {
        response.setContentType("text/html; charset= UTF-8");
        response.setCharacterEncoding("UTF8");

        PrintWriter out = response.getWriter();
        rentService.returnBook(rent_id, out);
    }
    //예약
    @PostMapping("/reservation")
    public String reservation(
            @RequestParam(value = "book_id") Long book_id,
            @RequestParam(value = "student_id") String student_id) {

        reserveService.makeReservation(book_id, student_id);

        return "redirect:/";
    }


}
