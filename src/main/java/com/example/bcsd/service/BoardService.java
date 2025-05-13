package com.example.bcsd.service;

import com.example.hellospring.domain.Board;
import com.example.hellospring.dto.BoardRequestDto;
import com.example.hellospring.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new NullPointerException(""));
    }

    public Board createBoard(BoardRequestDto dto) {
        Board board = new Board(dto.getId(), dto.getName());
        return boardRepository.save(board);
    }

    public void deleteBoard(Long id) {
        if (!boardRepository.deleteById(id)) {
            throw new NullPointerException("");
        }
    }
}