package com.example.bcsd.postview.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bcsd.board.dto.BoardWithArticles;
import com.example.bcsd.board.service.BoardService;

@Controller
public class PostViewController {
    private final BoardService boardService;

    public PostViewController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/posts")
    public String PostView(Model model) {
        List<BoardWithArticles> boards = boardService.GetBoardsWithArticles();
        model.addAttribute("boards", boards);

        return "postView";
    }
}
