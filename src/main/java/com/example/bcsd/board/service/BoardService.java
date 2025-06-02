package com.example.bcsd.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bcsd.board.model.Board;
import com.example.bcsd.article.dto.GetArticlesResponse;
import com.example.bcsd.article.service.ArticleService;
import com.example.bcsd.board.dto.BoardWithArticles;
import com.example.bcsd.board.dto.CreateBoardRequest;
import com.example.bcsd.board.dto.UpdateBoardRequest;
import com.example.bcsd.board.dto.UpdateBoardResponse;
import com.example.bcsd.board.repository.BoardRepository;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final ArticleService articleService;

    public BoardService(BoardRepository boardRepository, ArticleService articleService) {
        this.boardRepository = boardRepository;
        this.articleService = articleService;
    }

    @Transactional
    public void CreateBoard(CreateBoardRequest request) {
        boardRepository.save(request.toBoard());
    }

    public List<Board> GetBoards() {
        return boardRepository.findAll();
    }

    public List<BoardWithArticles> GetBoardsWithArticles() {
        List<Board> boards = GetBoards();

        return boards.stream().map(board -> {
            GetArticlesResponse articles = articleService.GetArticlesByBoardId(board.getId());
            return new BoardWithArticles(board, articles);
        }).collect(Collectors.toList());
    }

    public Board GetBoard(Long id) {
        return boardRepository.findById(id);
    }

    @Transactional
    public UpdateBoardResponse UpdateBoard(Long id, UpdateBoardRequest request) {
        Board board = GetBoard(id);
        board.updateName(request.name());

        boardRepository.updateSave(board);

        return UpdateBoardResponse.from(board);
    }

    @Transactional
    public void DeleteBoard(Long id) {
        boardRepository.delete(id);
    }
}
