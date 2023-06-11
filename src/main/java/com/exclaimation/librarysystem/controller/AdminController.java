package com.exclaimation.librarysystem.controller;

import com.exclaimation.librarysystem.entity.RentEntity;
import com.exclaimation.librarysystem.entity.Require;
import com.exclaimation.librarysystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/adminIndex")
    public String searchPage() {
        return "admin/adminIndex";
    }

    // 회원들 대출 목록 보기
    @GetMapping("/rentList")
    public String rentList(Model model) {
        List<RentEntity> rentList =  adminService.showRentList();
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
