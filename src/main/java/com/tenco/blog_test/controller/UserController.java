package com.tenco.blog_test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    // 회원가입 화면
    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    // 로그인 화면
    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    // 회원 정보보기 화면
    @GetMapping("/user/update-form")
    public String updateForm() {
        return "user/update-form";
    }

    // 로그아웃 클릭시 메인화면 돌아가기
    @GetMapping("/logout")
    public String logout () {
        return "redirect:/";
    }
}
