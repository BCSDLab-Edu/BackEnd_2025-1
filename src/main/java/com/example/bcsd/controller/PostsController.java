package com.example.bcsd.controller;

import com.example.bcsd.dto.ArticleResponseDto;
import com.example.bcsd.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostsController {

    private final ArticleService articleService;

    public PostsController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/posts")
    public String getPosts(@RequestParam Long boardId, Model model) {
        List<ArticleResponseDto> articles = articleService.getAllByBoardId(boardId);
        String boardName = articleService.getBoardName(boardId);

        model.addAttribute("boardName", boardName);
        model.addAttribute("articles", articles);

        return "posts"; // resources/templates/posts.html 로 연결
    }
}
