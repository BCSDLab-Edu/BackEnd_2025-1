package com.example.bcsd;

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
        List<ArticleDto> list = articleService.getAllByBoardId(boardId);
        String boardName = list.isEmpty() ? "게시판" : list.get(0).getBoardName();
        model.addAttribute("boardName", boardName);
        model.addAttribute("articles", list);
        return "posts";
    }
}
