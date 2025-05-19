package com.example.bcsd.controller;

import com.example.bcsd.dto.BoardResponseDto;
import com.example.bcsd.service.ArticleService;
import com.example.bcsd.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    private final ArticleService articleService;
    private final BoardService boardService;

    public BoardController(ArticleService articleService, BoardService boardService) {
        this.articleService = articleService;
        this.boardService = boardService;
    }

    @GetMapping("/posts")
    public String posts(@RequestParam Long boardId, Model model) {
        BoardResponseDto board = boardService.findById(boardId);
        model.addAttribute("boardName", board.name());
        model.addAttribute("articles", articleService.findByBoardId(boardId));
        return "posts"; // templates/posts.html
    }
}
