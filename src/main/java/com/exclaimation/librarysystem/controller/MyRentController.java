package com.exclaimation.librarysystem.controller;

import com.exclaimation.librarysystem.entity.RentEntity;
import com.exclaimation.librarysystem.service.MyRentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// 나의 대출 현황
@Controller
public class MyRentController {

    private final MyRentService myRentService;

    public MyRentController(MyRentService myRentService) {
        this.myRentService = myRentService;
    }

    @GetMapping("/myRentList")
    public String myRentList(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List<RentEntity> rentList = myRentService.myRentList(userDetails.getUsername());
        model.addAttribute("rentList", rentList);

        return "/myRentList";
    }
}
