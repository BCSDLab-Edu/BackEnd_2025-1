package com.example.bcsd.service;

import com.example.bcsd.domain.Board;
import com.example.bcsd.domain.Member;
import com.example.bcsd.dto.BoardRequestDto;
import com.example.bcsd.exception.*;
import com.example.bcsd.repository.ArticleRepository;
import com.example.bcsd.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final ArticleRepository articleRepository;

    public BoardService(BoardRepository boardRepository, ArticleRepository articleRepository) {
        this.boardRepository = boardRepository;
        this.articleRepository = articleRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException(ErrorCode.CANNOT_FIND_BOARD));
    }

    public String getBoardNameById(Long id) {
        return boardRepository.findNameById(id)
                .orElseThrow(() -> new BoardNotFoundException(ErrorCode.CANNOT_FIND_BOARD));
    }

    @Transactional
    public Board createBoard(BoardRequestDto dto) {
        Board board = new Board(dto.getId(), dto.getName());
        return boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException(ErrorCode.CANNOT_FIND_BOARD));

        if (articleRepository.existsByWriterId(id)) {
            throw new BoardDeletionNotAllowedException(ErrorCode.MEMBER_HAS_ARTICLES);
        }
    }
}