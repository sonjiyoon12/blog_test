package com.tenco.blog_test.controller;

import com.tenco.blog_test.model.Board;
import com.tenco.blog_test.repository.BoardNativeRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller // 웹 요청을 받아서 처리하고, 뷰(View)를 반환
public class BoardController {

    @Autowired
    private BoardNativeRepository br;

    // 게시글 수정 후 페이지요청
    @PostMapping("/board/{id}/update-form")
    public String update(@PathVariable (name = "id") Long id,
                         @RequestParam(name = "title") String title,
                         @RequestParam(name = "content") String content,
                         @RequestParam(name = "username") String username,
                         HttpServletRequest request) {
        br.updateById(id, title, content, username);
        return "redirect:/board/" + id;

    }


    //게시글 수정 화면 페이지 요청
    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable (name = "id") Long id, HttpServletRequest request) {
        Board board = br.findById(id);
        request.setAttribute("board", board);

        return "/board/update-form";

    }


    // 게시글 삭제 후 화면 요청
    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable(name = "id") Long id) {
        br.deleteById(id);
        return "redirect:/";
    }

    //상세보기 화면
    @GetMapping("/board/{id}")
    public String detail(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        Board board = br.findById(id);
        request.setAttribute("board", board);
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

        br.save(title,content,username);
        return "redirect:/";
    }

    // 메인 화면
    @GetMapping({"/", "index"})
    public String index(HttpServletRequest request) {
        List<Board> boardList = br.findAll();
        request.setAttribute("boardList", boardList);
        return "index";
    }


    // 글쓰기 화면
    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }


}
