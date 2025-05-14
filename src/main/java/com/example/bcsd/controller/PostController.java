package com.example.bcsd.controller;

import com.example.bcsd.dto.ArticleListResponseDto;
import com.example.bcsd.dto.ArticleResponseDto;
import com.example.bcsd.service.ArticleService;
import com.example.bcsd.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {
    private final ArticleService articleService;
    private final BoardService boardService;

    public PostController(ArticleService articleService, BoardService boardService) {
        this.articleService = articleService;
        this.boardService = boardService;
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "postView";
    }

    @GetMapping(value = "/posts", params = "boardId")
    public String getPostViewByBoardId(@RequestParam Long boardId, Model model) {
        String boardName = boardService.getBoardNameById(boardId); // boardId로 이름 가져오는 메소드
        List<ArticleResponseDto> articles = articleService.getArticlesByBoardId(boardId);

        model.addAttribute("boardName", boardName);
        model.addAttribute("articles", articles);

        return "postViewByBoard"; // 위에 작성한 HTML 파일 이름
    }
}
