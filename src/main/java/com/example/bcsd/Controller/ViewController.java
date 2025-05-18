package com.example.bcsd.Controller;

import com.example.bcsd.Board;
import com.example.bcsd.Service.ArticleService;
import com.example.bcsd.Service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    private final ArticleService articleService;
    private final BoardService boardService;

    public ViewController(ArticleService articleService, BoardService boardService) {
        this.articleService = articleService;
        this.boardService = boardService;
    }

    @GetMapping("/posts")
    public String getPostView(@RequestParam Long boardId, Model model) {
        model.addAttribute("boardName", boardService.findById(boardId).getName());
        model.addAttribute("articles", articleService.findByBoardId(boardId));
        return "posts";  // templates/posts.html
    }

    @GetMapping("/posts")
    public String getPosts(@RequestParam Long boardId, Model model) {
        Board board = boardService.findById(boardId);
        model.addAttribute("boardName", board.getName());
        model.addAttribute("articles", articleService.findByBoardId(boardId));
        return "posts"; // templates/posts.html
    }

}
