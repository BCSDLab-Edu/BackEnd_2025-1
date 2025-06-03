package com.example.bcsd.service;

import org.springframework.stereotype.Service;

import com.example.bcsd.dto.RequestDto.BoardCreateRequestDto;
import com.example.bcsd.dto.ResponseDto.BoardResponseDto;
import com.example.bcsd.exception.NotFoundException;
import com.example.bcsd.exception.NullRequestException;
import com.example.bcsd.model.Board;
import com.example.bcsd.repository.ArticleRepository;
import com.example.bcsd.repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
    
    private final BoardRepository boardRepository;

    
    private final ArticleRepository articleRepository;

    public Board getBoardM(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException("게시판이 없습니다."));
        return board;
    }

    public BoardResponseDto getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException("게시판이 없습니다."));
        return BoardResponseDto.from(board);
    }

    @Transactional
    public BoardResponseDto createBoard(BoardCreateRequestDto requestDto) {
        if (requestDto.getTitle() == null) {
            throw new NullRequestException("게시판 제목이 없습니다.");
        }
        Board board = Board.builder()
                .title(requestDto.getTitle())
                .build();
        boardRepository.save(board);
        return BoardResponseDto.from(board);
    }

    @Transactional
    public BoardResponseDto updateBoard(Long boardId, BoardCreateRequestDto requestDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException("게시판이 없습니다."));
        if (requestDto.getTitle() == null) {
            throw new NullRequestException("게시판 제목이 없습니다.");
        }
        board.setTitle(requestDto.getTitle());
        boardRepository.save(board);
        return BoardResponseDto.from(board);
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException("게시판이 없습니다."));
        boardRepository.deleteById(boardId);
    }
}
