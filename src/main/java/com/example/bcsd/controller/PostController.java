package com.example.bcsd.controller;

import com.example.hellospring.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
    private final ArticleService articleService;

    public PostController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "postView";
    }
}
