package com.example.bcsd;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    List<Article> articleList = new ArrayList<>();
    int currentId = 1;
}