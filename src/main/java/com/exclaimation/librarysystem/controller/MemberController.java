package com.exclaimation.librarysystem.controller;


import com.exclaimation.librarysystem.domain.Member;
import com.exclaimation.librarysystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        System.out.println("*** 회원가입 폼 실행 시작 ***");
        System.out.println("입력값 = " + form.getPw());
        Member member = new Member();
        member.setPw(form.getName());
        System.out.println("member.id = " + member.getId());
        System.out.println("member.pw = " + member.getPw());
        memberService.join(member);
        return "redirect:/";
    }
}

