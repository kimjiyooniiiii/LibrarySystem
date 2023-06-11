package com.exclaimation.librarysystem.controller;

import com.exclaimation.librarysystem.domain.Seat;
import com.exclaimation.librarysystem.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class IndexController {

    @GetMapping("")
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        if(userDetails != null) {
            model.addAttribute("loginId", userDetails.getUsername());
            model.addAttribute("loginRoles", userDetails.getAuthorities());
        }
        return "/index";
    }

    @PostMapping("/seat")
    public String seatInfo(@RequestParam(value="seat_id") Long seat_id,
                             @RequestParam(value="student_id") String student_id, Model model) {
        model.addAttribute("seatId", seat_id);
        return "index";
    }
}
