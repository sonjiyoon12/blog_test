package com.tenco.blog_test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 웹 요청을 받아서 처리하고, 뷰(View)를 반환
public class BoardController {

    // 글쓰기 화면
    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    // 메인 화면
    @GetMapping({"/index"})
    public String index() {
        return "index";
    }

    //상세보기 화면
    @GetMapping("/board/{id}")
    public String detail(@PathVariable(name = "id") Integer id) {
        return "board/detail";
    }

    // 게시글 작성
    @PostMapping("/board/save")
    public String save(@RequestParam("username") String username,
                       @RequestParam("title") String title,
                       @RequestParam("content") String content){
        System.out.println("title : " + title);
        System.out.println("content : " + content);
        System.out.println("usrname : " + username);
        return "redirect:/";
    }



}
