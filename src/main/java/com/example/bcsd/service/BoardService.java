package com.example.bcsd.service;

import com.example.bcsd.domain.Board;
import com.example.bcsd.dto.BoardRequestDto;
import com.example.bcsd.dto.BoardResponseDto;
import com.example.bcsd.repository.BoardDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private final BoardDao boardDao;

    public BoardService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Transactional
    public void create(BoardRequestDto dto) {
        boardDao.insert(new Board(null, dto.getName()));
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDto> getAll() {
        return boardDao.findAll().stream()
                .map(b -> new BoardResponseDto(b.getId(), b.getName()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BoardResponseDto getById(Long id) {
        Board board = boardDao.findById(id);
        return new BoardResponseDto(board.getId(), board.getName());
    }

    @Transactional
    public void update(Long id, BoardRequestDto dto) {
        boardDao.update(id, new Board(id, dto.getName()));
    }

    @Transactional
    public void delete(Long id) {
        boardDao.delete(id);
    }
}
