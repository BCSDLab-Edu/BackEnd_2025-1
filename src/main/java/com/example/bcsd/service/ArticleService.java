package com.example.bcsd.service;

import com.example.bcsd.domain.Article;
import com.example.bcsd.domain.Board;
import com.example.bcsd.domain.Member;
import com.example.bcsd.dto.ArticleRequestDto;
import com.example.bcsd.dto.ArticleResponseDto;
import com.example.bcsd.exception.NotFoundException;
import com.example.bcsd.exception.InvalidReferenceException;
import com.example.bcsd.repository.ArticleDao;
import com.example.bcsd.repository.BoardDao;
import com.example.bcsd.repository.MemberDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ArticleDao articleDao;
    private final BoardDao boardDao;
    private final MemberDao memberDao;

    public ArticleService(ArticleDao articleDao, BoardDao boardDao, MemberDao memberDao) {
        this.articleDao = articleDao;
        this.boardDao = boardDao;
        this.memberDao = memberDao;
    }

    @Transactional
    public void addArticle(ArticleRequestDto dto) {
        validateArticleReferences(dto.getAuthorId(), dto.getBoardId());
        Article article = new Article(null, dto.getAuthorId(), dto.getBoardId(), dto.getTitle(), dto.getContent(), LocalDateTime.now(), LocalDateTime.now());
        articleDao.insert(article);
    }

    @Transactional
    public Article addAndReturn(ArticleRequestDto dto) {
        validateArticleReferences(dto.getAuthorId(), dto.getBoardId());
        Article article = new Article(null, dto.getAuthorId(), dto.getBoardId(), dto.getTitle(), dto.getContent(), LocalDateTime.now(), LocalDateTime.now());
        articleDao.insert(article);
        return articleDao.findLatest();
    }

    @Transactional(readOnly = true)
    public List<ArticleResponseDto> getAllByBoardId(Long boardId) {
        if (boardDao.findById(boardId) == null) {
            throw new NotFoundException("게시판을 찾을 수 없습니다.");
        }
        return articleDao.findAllByBoardId(boardId);
    }

    @Transactional(readOnly = true)
    public List<Article> getAllArticles() {
        return articleDao.findAll();
    }

    @Transactional(readOnly = true)
    public Article getArticleById(Long id) {
        return Optional.ofNullable(articleDao.findById(id))
                .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));
    }

    @Transactional
    public void updateArticle(Long id, ArticleRequestDto dto) {
        validateArticleReferences(dto.getAuthorId(), dto.getBoardId());
        Article article = new Article(id, dto.getAuthorId(), dto.getBoardId(), dto.getTitle(), dto.getContent(), null, LocalDateTime.now());
        articleDao.update(id, article);
    }

    @Transactional
    public void deleteArticle(Long id) {
        articleDao.delete(id);
    }

    @Transactional(readOnly = true)
    public String getBoardName(Long boardId) {
        Board board = boardDao.findById(boardId);
        if (board == null) {
            throw new NotFoundException("게시판을 찾을 수 없습니다.");
        }
        return board.getName();
    }

    private void validateArticleReferences(Long authorId, Long boardId) {
        if (memberDao.findById(authorId) == null) {
            throw new InvalidReferenceException("존재하지 않는 사용자입니다.");
        }
        if (boardDao.findById(boardId) == null) {
            throw new InvalidReferenceException("존재하지 않는 게시판입니다.");
        }
    }
}
