package com.exclaimation.librarysystem.controller;

import com.exclaimation.librarysystem.dto.BookData;
import com.exclaimation.librarysystem.entity.RentEntity;
import com.exclaimation.librarysystem.entity.Require;
import com.exclaimation.librarysystem.entity.ReserveEntity;
import com.exclaimation.librarysystem.service.AdminService;
import com.exclaimation.librarysystem.service.BookService;
import com.exclaimation.librarysystem.service.RentService;
import com.exclaimation.librarysystem.service.ReserveService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private final AdminService adminService;
    private final BookService bookService;
    private final RentService rentService;
    private final ReserveService reserveService;

    public AdminController(AdminService adminService, BookService bookService, RentService rentService, ReserveService reserveService) {
        this.adminService = adminService;
        this.bookService = bookService;
        this.rentService = rentService;
        this.reserveService = reserveService;
    }

    @GetMapping("/")
    public String searchPage() {
        return "admin/adminIndex";
    }

    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam("keyword") String keyword,
                         @RequestParam(value = "page", defaultValue = "0") int page) {

        model.addAttribute("books", bookService.search(keyword, page));
        model.addAttribute("keyword", keyword);
        model.addAttribute("maxPage", 5);
        model.addAttribute("userRole", "ADMIN");

        return "/admin/search";
    }

    @GetMapping("/userId")
    public String getUserIdForm(Model model, @RequestParam("bookId") Long bookId) {
        model.addAttribute("bookId", bookId);
        return "admin/userId";
    }

    @PostMapping("/rent")
    public void rentBook(HttpServletResponse response, BookData.rentRequest book) throws IOException {

        response.setContentType("text/html; charset= UTF-8");
        response.setCharacterEncoding("UTF8");

        PrintWriter out = response.getWriter();

        // 예약자 중 가장 빠른 사람에게 대출
        boolean isReserve = reserveService.checkReservationIdByBookId(book.getBookId(), book.getStudentId());
        if (isReserve) {
            out.println("<script>alert('예약된 도서입니다'); window.close();</script> ");
            out.flush();
        }else{
            if(rentService.isRent(book.getBookId(), book.getStudentId())){
                out.println("<script>alert('대출자가 이미 존재합니다'); window.close();</script> ");
                out.flush();
                return;
            }
            rentService.rent(book.getBookId(), book.getStudentId());
            out.println("<script>alert('정상적으로 대출이 완료되었습니다.'); window.close();</script> ");
        }
    }

    // 회원들 대출 목록 보기
    @GetMapping("/rentList")
    public String rentList(Model model) {
        List<RentEntity> rentList = adminService.showRentList();
        model.addAttribute("rentList", rentList);

        return "admin/rentList";
    }

    // 희망 도서 목록 보기
    @GetMapping("/requireList")
    public String requireList(Model model) {
        List<Require> requireList = adminService.showRequireList();
        model.addAttribute("requireList", requireList);

        return "admin/requireList";
    }
}
