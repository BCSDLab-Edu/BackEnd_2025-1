package com.example.bcsd.service;

import com.example.bcsd.dao.ArticleDao;
import com.example.bcsd.domain.Article;
import com.example.bcsd.dto.ArticleDto;
import com.example.bcsd.dto.ArticleResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleDao articleDao;

    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public List<ArticleResponseDto> findByBoardId(Long boardId) {
        return articleDao.findByBoardId(boardId).stream()
                .map(this::toDto)
                .toList();
    }

    public ArticleResponseDto findById(Long id) {
        Article article = articleDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 없습니다."));
        return toDto(article);
    }

    @Transactional
    public ArticleResponseDto create(ArticleDto dto) {
        LocalDateTime now = LocalDateTime.now();
        Article article = new Article(null, dto.authorId(), dto.boardId(),
                dto.title(), dto.content(), now, now);
        return toDto(articleDao.save(article));
    }

    @Transactional
    public ArticleResponseDto update(Long id, ArticleDto dto) {
        Article updated = new Article(id, dto.authorId(), dto.boardId(),
                dto.title(), dto.content(), null, LocalDateTime.now());
        articleDao.update(id, updated);
        return findById(id);
    }

    @Transactional
    public void delete(Long id) {
        articleDao.delete(id);
    }

    private ArticleResponseDto toDto(Article a) {
        return new ArticleResponseDto(
                a.id(), a.authorId(), a.boardId(), a.title(), a.content(),
                a.createdDate(), a.modifiedDate()
        );
    }
}
