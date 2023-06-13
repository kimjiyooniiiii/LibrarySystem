package com.exclaimation.librarysystem.controller;

import com.exclaimation.librarysystem.entity.Student;
import com.exclaimation.librarysystem.service.RentService;
import com.exclaimation.librarysystem.service.SeatService;
import com.exclaimation.librarysystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final SeatService seatService;
    private final RentService rentService;
    private final StudentService studentService;

    @Autowired
    public IndexController(SeatService seatService, RentService rentService, StudentService studentService) {
        this.seatService = seatService;
        this.rentService = rentService;
        this.studentService = studentService;
    }

    @GetMapping("")
    public String index(@AuthenticationPrincipal User userDetails, Model model) throws IllegalAccessException {

        if(userDetails != null) {
            // 현재 사용중인 좌석번호 출력
            Long seatId = seatService.getSeatIdByStudentId(userDetails.getUsername());
            if(seatId != 0l)
                model.addAttribute("seatId", seatId);
            else
                model.addAttribute("seatId", null);

            // 현재 대출권수, 연체권수 출력
            int rentBookCnt = rentService.getRentBookCnt(userDetails.getUsername());
            int delayBookCnt = rentService.getDelayBookCnt(userDetails.getUsername());

            if(delayBookCnt > 0){
                studentService.setDelay(userDetails.getUsername(), true);
            }
            else {
                studentService.setDelay(userDetails.getUsername(), false);
            }

            model.addAttribute("rentBookCnt", rentBookCnt);
            model.addAttribute("delayBookCnt", delayBookCnt);


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
