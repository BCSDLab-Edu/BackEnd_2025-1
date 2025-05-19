package com.example.bcsd.controller;

import com.example.bcsd.model.Article;
import com.example.bcsd.model.Board;
import com.example.bcsd.repository.BoardRepository;
import com.example.bcsd.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostsController {

    private final ArticleService articleService;
    private final BoardRepository boardRepository;

    @Autowired
    public PostsController(ArticleService articleService, BoardRepository boardRepository) {
        this.articleService = articleService;
        this.boardRepository = boardRepository;
    }

    @GetMapping("/posts")
    public String posts(@RequestParam("boardId") Long boardId, Model model) {
        List<Article> articles = articleService.getArticlesByBoardId(boardId);

        String boardName = "게시판 없음";
        if (!articles.isEmpty()) {
            Board board = boardRepository.findById(boardId).orElse(null);
            if (board != null) {
                boardName = board.getBoardName();
            }
        }

        model.addAttribute("boardName", boardName);
        model.addAttribute("articles", articles);
        return "posts"; // resources/templates/posts.html 렌더링
    }
}
