package com.example.bcsd.service;

import com.example.bcsd.dao.BoardDao;
import com.example.bcsd.domain.Board;
import com.example.bcsd.dto.BoardDto;
import com.example.bcsd.dto.BoardResponseDto;
import com.example.bcsd.exception.CustomException;
import com.example.bcsd.exception.ExceptionStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    private final BoardDao boardDao;

    public BoardService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public BoardResponseDto findById(Long id) {
        Board board = boardDao.findById(id)
                .orElseThrow(() -> new CustomException(ExceptionStatus.BOARD_NOT_EXIST));
        return new BoardResponseDto(board.getId(), board.getName());
    }

    public BoardResponseDto create(BoardDto dto) {
        if(dto.boardId() == null || dto.name() == null){
            throw new CustomException(ExceptionStatus.BOARD_NOT_EXIST);
        }
        Board saved = boardDao.save(new Board(null, dto.name()));
        return new BoardResponseDto(saved.getId(), saved.getName());
    }

    @Transactional
    public void delete(Long boardId) {
        if (boardDao.findById(boardId).isEmpty()) {
            throw new CustomException(ExceptionStatus.BOARD_NOT_EXIST); // 404
        }

        if(boardDao.existArticleOnBoard(boardId)) {
            throw new CustomException(ExceptionStatus.ARTICLE_EXISTS_ON_BOARD);
        }
        boardDao.delete(boardId);
    }
}
