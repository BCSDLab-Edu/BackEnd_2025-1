package com.example.bcsd.controller;

import com.example.bcsd.domain.Board;
import com.example.bcsd.dto.BoardRequestDto;
import com.example.bcsd.dto.BoardResponseDto;
import com.example.bcsd.service.BoardService;
import jakarta.validation.Valid;
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
    public List<BoardResponseDto> getAllBoards() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{id}")
    public BoardResponseDto getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }

    @PostMapping
    public BoardResponseDto createBoard(@RequestBody @Valid BoardRequestDto dto) {
        return boardService.createBoard(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }
}