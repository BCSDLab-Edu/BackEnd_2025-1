package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class PostController {
    // GET /posts
    @GetMapping("/posts")
    public String showPosts(Model model) {
        model.addAttribute("articles", ArticleService.articleList); // static 리스트 사용
        return "posts"; // templates/posts.html 렌더링
    }
}