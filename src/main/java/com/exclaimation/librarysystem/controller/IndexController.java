package com.exclaimation.librarysystem.controller;

import com.exclaimation.librarysystem.service.RentService;
import com.exclaimation.librarysystem.service.SeatService;
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

    @Autowired
    public IndexController(SeatService seatService, RentService rentService) {
        this.seatService = seatService;
        this.rentService = rentService;
    }

    @GetMapping("")
    public String index(@AuthenticationPrincipal User userDetails, Model model) {

        if(userDetails != null) {
            Long seatId = seatService.getSeatIdByStudentId(userDetails.getUsername());
            if(seatId != 0l)
                model.addAttribute("seatId", seatId);
            else
                model.addAttribute("seatId", null);

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
