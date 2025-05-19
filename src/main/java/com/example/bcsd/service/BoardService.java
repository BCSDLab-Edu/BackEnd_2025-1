package com.example.bcsd.service;

import com.example.bcsd.dao.BoardDao;
import com.example.bcsd.domain.Board;
import com.example.bcsd.dto.BoardDto;
import com.example.bcsd.dto.BoardResponseDto;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private final BoardDao boardDao;

    public BoardService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public BoardResponseDto findById(Long id) {
        Board board = boardDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시판이 없습니다."));
        return new BoardResponseDto(board.id(), board.name());
    }

    public BoardResponseDto create(BoardDto dto) {
        Board saved = boardDao.save(new Board(null, dto.name()));
        return new BoardResponseDto(saved.id(), saved.name());
    }
}
