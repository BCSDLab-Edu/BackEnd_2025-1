package com.example.bcsd.service;

import com.example.bcsd.dao.ArticleDao;
import com.example.bcsd.domain.Article;
import com.example.bcsd.dto.ArticleDto;
import com.example.bcsd.dto.ArticleResponseDto;
import com.example.bcsd.exception.CustomException;
import com.example.bcsd.exception.ExceptionStatus;
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
        List<Article> articles = articleDao.findByBoardId(boardId);
        if(articles.isEmpty()){
            throw new CustomException(ExceptionStatus.ARTICLE_NOT_EXIST);
        }
        return articleDao.findByBoardId(boardId).stream()
                    .map(this::toDto)
                    .toList();
    }

    public ArticleResponseDto findById(Long id) {
        Article article = articleDao.findById(id)
                .orElseThrow(() -> new CustomException(ExceptionStatus.ARTICLE_NOT_EXIST));
        return toDto(article);
    }

    @Transactional
    public ArticleResponseDto create(ArticleDto dto) {
        if(dto.boardId()==null || dto.authorId()==null || dto.title()==null || dto.content()==null){
            throw new CustomException(ExceptionStatus.ARTICLE_HAS_NULL);
        }

        if(articleDao.findById(dto.authorId()).isEmpty()){
            throw new CustomException(ExceptionStatus.ARTICLE_MEMBER_NOT_EXIST);
        }

        if(articleDao.findById(dto.boardId()).isEmpty()){
            throw new CustomException(ExceptionStatus.ARTICLE_BOARD_NOT_EXIST);
        }
        LocalDateTime now = LocalDateTime.now();
        Article article = new Article(null, dto.authorId(), dto.boardId(),
                dto.title(), dto.content(), now, now);

        return toDto(articleDao.save(article));
    }

    @Transactional
    public ArticleResponseDto update(Long id, ArticleDto dto) {
        if(dto.authorId()==null){
            throw new CustomException(ExceptionStatus.ARTICLE_MEMBER_NOT_EXIST);
        }
        if(dto.boardId()==null){
            throw new CustomException(ExceptionStatus.ARTICLE_BOARD_NOT_EXIST);
        }

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
                a.getId(), a.getAuthorId(), a.getBoardId(), a.getTitle(), a.getContent(),
                a.getCreatedDate(), a.getModifiedDate()
        );
    }
}
