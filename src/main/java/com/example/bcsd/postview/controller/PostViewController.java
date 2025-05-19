package com.example.bcsd.postview.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bcsd.article.dto.GetArticlesResponse;
import com.example.bcsd.article.service.ArticleService;
import com.example.bcsd.board.dto.BoardWithArticles;
import com.example.bcsd.board.model.Board;
import com.example.bcsd.board.service.BoardService;

@Controller
public class PostViewController {
    private final BoardService boardService;
    private final ArticleService articleService;

    public PostViewController(BoardService boardService, ArticleService articleService) {
        this.boardService = boardService;
        this.articleService = articleService;
    }

    @GetMapping("/posts")
    public String PostView(
        Model model,
        @RequestParam(value = "boardId", required = false) Long boardId
    ) {
        if (boardId == null) {
            List<BoardWithArticles> boards = boardService.GetBoardsWithArticles();
            model.addAttribute("boards", boards);
    
            return "postView";
        } else {
            Board board = boardService.GetBoard(boardId);
            GetArticlesResponse articles = articleService.GetArticlesByBoardId(boardId);

            model.addAttribute("board", board);
            model.addAttribute("articles", articles);

            return "singlePostView";
        }
    }
}
