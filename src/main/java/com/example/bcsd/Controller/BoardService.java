package com.example.bcsd.Service;

import com.example.bcsd.Board;
import com.example.bcsd.Repository.BoardRepository;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board findById(Long id) {
        return boardRepository.findById(id);
    }
}
