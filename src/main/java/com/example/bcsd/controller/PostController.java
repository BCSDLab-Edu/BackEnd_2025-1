package com.example.bcsd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bcsd.service.ArticleService;
import com.example.bcsd.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final ArticleService articleService;
    private final BoardService boardService;

    @GetMapping
    public String getPosts(@RequestParam Long boardId, Model model) {
        model.addAttribute("board", boardService.getBoardM(boardId));
        model.addAttribute("articles", articleService.getArticlesByBoardId(boardId));
        return "posts";
    }

}
