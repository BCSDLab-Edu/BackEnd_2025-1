package com.example.bcsd.service;

import com.example.bcsd.domain.Board;
import com.example.bcsd.dto.BoardRequestDto;
import com.example.bcsd.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public String getBoardNameById(Long id) {
        return boardRepository.findNameById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시판이 존재하지 않습니다."));
    }

    @Transactional
    public Board createBoard(BoardRequestDto dto) {
        Board board = new Board(dto.getId(), dto.getName());
        return boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long id) {
        if (!boardRepository.deleteById(id)) {
            throw new NullPointerException("");
        }
    }
}