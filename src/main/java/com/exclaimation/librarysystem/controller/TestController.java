package com.exclaimation.librarysystem.controller;

import com.exclaimation.librarysystem.domain.Seat;
import com.exclaimation.librarysystem.entity.ReserveEntity;
import com.exclaimation.librarysystem.service.RentService;
import com.exclaimation.librarysystem.service.ReserveService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "test";
    }

    private final ReserveService reserveService;
    private final RentService rentService;

    @Autowired
    public TestController(ReserveService reserveService, RentService rentService) {
        this.reserveService = reserveService;
        this.rentService = rentService;
    }

    @GetMapping("/rent")
    public String rent() throws IllegalAccessException {

        // 예약자 중 가장 빠른 사람에게 대출
        Long r_id = reserveService.fastReservationIdByBookId(1l);
        if (r_id == 0) {
            System.out.println("예약자가 없습니다");
        } else {
            ReserveEntity reserveEntity = reserveService.findById(r_id);
            int result = rentService.rent(reserveEntity.getBook_id(), reserveEntity.getStudent_id());

            if (result != 0) {
                System.out.println("대출 insert 에러발생");
                return "rent";
            }

            result = reserveService.deleteById(r_id);
            if (result != 0) {
                System.out.println("예약 delete 에러 발생");
                return "rent";
            }
        }

        return "rent";
    }

    @GetMapping("/return")
    public String returnBook() {
        int result = rentService.returnBook(1l);

        return "rent";
    }

    @PostMapping("/reservation")
    public void reservation(
            HttpServletResponse response,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(value = "book_id") Long book_id) throws IOException {


        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        if(userDetails != null){
            reserveService.makeReservation(book_id, userDetails.getUsername(), out);
        }
        else{
            out.println("<script>alert('로그인 후 이용바랍니다.'); window.location.href = '/';</script>");
            out.flush();
        }


    }



}
