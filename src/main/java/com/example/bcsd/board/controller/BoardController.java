package com.example.bcsd.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.bcsd.board.dto.CreateBoardRequest;
import com.example.bcsd.board.dto.GetBoardsResponse;
import com.example.bcsd.board.dto.UpdateBoardRequest;
import com.example.bcsd.board.dto.UpdateBoardResponse;
import com.example.bcsd.board.model.Board;
import com.example.bcsd.board.service.BoardService;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<Void> CreateBoard(
            @RequestBody CreateBoardRequest request
    ) {
        boardService.CreateBoard(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<GetBoardsResponse> GetBoards() {
        List<Board> boards = boardService.GetBoards();

        return ResponseEntity.ok().body(new GetBoardsResponse(boards, boards.size()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateBoardResponse> UpdateBoard(
        @PathVariable("id") Long id,
        @RequestBody UpdateBoardRequest request
    ) {
        UpdateBoardResponse res = boardService.UpdateBoard(id, request);

        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteBoard(
        @PathVariable("id") Long id
    ) {
        boardService.DeleteBoard(id);

        return ResponseEntity.noContent().build();
    }
}
