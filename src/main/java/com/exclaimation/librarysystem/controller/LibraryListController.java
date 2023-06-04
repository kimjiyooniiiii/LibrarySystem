//package com.exclaimation.librarysystem.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.Mapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/li")
//public class LibraryListController {
//
//
//    @GetMapping("/")
//    public String findList(Model model) {
//        Map<String, Object> book1 = new HashMap<>();
//        Map<String, Object> book2 = new HashMap<>();
//        Map<String, Object> book3 = new HashMap<>();
//        Map<String, Object> book4 = new HashMap<>();
//        book1.put("id", "4432");
//        book1.put("name", "주린이도 술술 읽는 친절한 경제책");
//        book1.put("available", "대출 가능");
//        book2.put("id", "4434");
//        book2.put("name", "오늘부터 제대로, 금융 공부");
//        book2.put("available", "대출 가능");
//        book3.put("id", "1235");
//        book3.put("name", "자본론");
//        book3.put("available", "대출 불가능");
//        book4.put("id", "1223");
//        book4.put("name", "부의 감각");
//        book4.put("available", "대출 불가능");
//        List<Map<String, Object>> books = new ArrayList<>();
//        books.add(book1);
//        books.add(book2);
//        books.add(book3);
//        books.add(book4);
//
//        model.addAttribute("books", books);
//        model.addAttribute("keyword", "경제");
//
//        return "/search";
//    }
//
//
//    @GetMapping("/")
//    public String rentList(Model model) {
//        Map<String, Object> book1 = new HashMap<>();
//        Map<String, Object> book2 = new HashMap<>();
//        Map<String, Object> book3 = new HashMap<>();
//        Map<String, Object> book4 = new HashMap<>();
//        book1.put("id", "4432");
//        book1.put("name", "주린이도 술술 읽는 친절한 경제책");
//        book1.put("available", "대출 가능");
//        book2.put("id", "4434");
//        book2.put("name", "오늘부터 제대로, 금융 공부");
//        book2.put("available", "대출 가능");
//        book3.put("id", "1235");
//        book3.put("name", "자본론");
//        book3.put("available", "대출 불가능");
//        book4.put("id", "1223");
//        book4.put("name", "부의 감각");
//        book4.put("available", "대출 불가능");
//        List<Map<String, Object>> books = new ArrayList<>();
//        books.add(book1);
//        books.add(book2);
//        books.add(book3);
//        books.add(book4);
//
//        model.addAttribute("books", books);
//        model.addAttribute("keyword", "경제");
//
//        return "/list";
//    }
//
//}
