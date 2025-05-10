package com.example.bcsd.board.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.bcsd.board.model.Board;
import com.example.bcsd.board.dto.CreateBoardRequest;
import com.example.bcsd.board.dto.UpdateBoardRequest;
import com.example.bcsd.board.dto.UpdateBoardResponse;
import com.example.bcsd.board.repository.BoardRepository;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void CreateBoard(CreateBoardRequest request) {
        boardRepository.save(request.toBoard());
    }

    public List<Board> GetBoards() {
        return boardRepository.findAll();
    }

    public Board GetBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found"));
    }

    public UpdateBoardResponse UpdateBoard(Long id, UpdateBoardRequest request) {
        Board board = GetBoard(id);
        board.setName(request.name());

        boardRepository.updateSave(board);
        return UpdateBoardResponse.from(board);
    }
}
