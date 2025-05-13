package com.example.bcsd.controller;

import com.example.hellospring.domain.Board;
import com.example.hellospring.dto.BoardRequestDto;
import com.example.hellospring.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{id}")
    public Board getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }

    @PostMapping
    public Board createBoard(@RequestBody BoardRequestDto dto) {
        return boardService.createBoard(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }
}