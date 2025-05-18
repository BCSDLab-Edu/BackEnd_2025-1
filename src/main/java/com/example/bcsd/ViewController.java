package com.example.bcsd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private ArticleController controller;

    public ViewController(ArticleController controller){
           this.controller = controller;
    }

    @GetMapping("/posts")
    public String showPosts(Model model) {
        model.addAttribute("articles", controller.getAllArticles());
        return "posts";
    }
}
