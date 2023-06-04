package com.exclaimation.librarysystem.controller;

import com.exclaimation.librarysystem.entity.Rent;
import com.exclaimation.librarysystem.entity.Require;
import com.exclaimation.librarysystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class adminController {

    @Autowired
    private final AdminService adminService;

    public adminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin/search")
    public String searchPage() {
        return "admin/admin_search";
    }

    // 회원들 대출 목록 보기
    @GetMapping("/rentList")
    public String rentList(Model model) {
        List<Rent> rentList =  adminService.showRentList();
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
