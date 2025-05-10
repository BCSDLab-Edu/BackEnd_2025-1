package com.example.bcsd.article.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.example.bcsd.article.dto.UpdateArticleRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.bcsd.article.model.Article;
import com.example.bcsd.article.dto.CreateArticleRequest;
import com.example.bcsd.article.dto.GetArticleResponse;
import com.example.bcsd.article.dto.GetArticlesResponse;
import com.example.bcsd.article.dto.UpdateArticleResponse;
import com.example.bcsd.article.repository.ArticleRepository;
import com.example.bcsd.member.repository.MemberRepository;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    public ArticleService(ArticleRepository articleRepository, MemberRepository memberRepository) {
        this.articleRepository = articleRepository;
        this.memberRepository = memberRepository;
    }

    public void CreateArticle(CreateArticleRequest request) {
        articleRepository.save(request.toArticle());
    }

    public GetArticlesResponse GetArticles() {
        List<Article> articles = articleRepository.findAll();
        List<Long> authorIds = articles.stream().map(Article::getAuthorId).distinct().toList();

        Map<Long, String> authorNames = new HashMap<>();
        for(Long id : authorIds) {
            memberRepository.findById(id).ifPresent(member -> authorNames.put(id, member.getName()));
        }

        List<GetArticleResponse> getArticleResponses = articles.stream().map(article -> {
            String authorName = authorNames.getOrDefault(article.getAuthorId(), "Unknown Author");
            return GetArticleResponse.from(article, authorName);
        }).toList();

        return new GetArticlesResponse(getArticleResponses, articles.size());
    }

    public GetArticleResponse GetArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found"));
        String author = memberRepository.findById(article.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found")).getName();
        return GetArticleResponse.from(article, author);
    }

    public UpdateArticleResponse updateArticle(Long id, UpdateArticleRequest request) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found"));
        String title = request.title();
        Long boardId = request.boardId();
        String content = request.content();

        if (title == null) {
            title = article.getTitle();
        }

        if (boardId == null) {
            boardId = article.getBoardId();
        }

        if (content == null) {
            content = article.getContent();
        }

        article.setTitle(title);
        article.setBoardId(boardId);
        article.setContent(content);
        article.setEditedDate(LocalDateTime.now());

        articleRepository.updateSave(article);
        return UpdateArticleResponse.from(article);
    }

    public void DeleteArticle(Long id) {
        articleRepository.delete(id);
    }
}
