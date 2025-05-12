package com.example.bcsd.controller;

import com.example.bcsd.service.ArticleService;
import com.example.bcsd.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PostsController {

    private final ArticleService articleService;

    @Autowired
    public PostsController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        LocalDateTime now = LocalDateTime.now();  // 현재 날짜와 시간
        List<Article> articleList = List.of(
                new Article("제목0", "회원0", now, ""),
                new Article("제목1", "회원1", now, "내용입니다!!"),
                new Article("제목2", "회원2", now, "내용입니다!!내용입니다!!")
        );
        model.addAttribute("articles", articleList);  // 게시물 목록을 모델에 추가
        return "posts";  // "posts.html" 템플릿을 렌더링
    }
}
