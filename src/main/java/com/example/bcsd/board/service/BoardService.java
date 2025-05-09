package com.example.bcsd.board.service;

import com.example.bcsd.board.repository.BoardRepository;

public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
}
