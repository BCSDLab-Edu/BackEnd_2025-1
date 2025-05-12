package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PostViewController {

    private final ArticleService service;

    public PostViewController(ArticleService service) {
        this.service = service;
    }

    // GET /posts → View 렌더링
    @GetMapping("/posts")
    public String viewAllArticles(Model model) {
        List<Article> articles = service.getAllArticles();
        model.addAttribute("articles", articles); // View에서 사용될 데이터
        return "posts"; // templates/posts.html
    }
}
