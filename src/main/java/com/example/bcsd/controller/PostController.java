package com.example.bcsd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bcsd.service.ArticleService;
import com.example.bcsd.service.BoardService;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private BoardService boardService;

    @GetMapping
    public String getPosts(Model model) {
        Long boardId = 1L; // 일단 이걸로 기준으로 board 가져온다고 생각함(과제 조건에 따라 Path로 안함)
        model.addAttribute("board", boardService.getBoard(boardId));
        model.addAttribute("articles", articleService.getArticlesByBoardId(boardId));
        return "posts";
    }

}
