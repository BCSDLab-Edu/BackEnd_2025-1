package com.example.bcsd.article.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

import com.example.bcsd.article.dto.UpdateArticleRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bcsd.article.model.Article;
import com.example.bcsd.article.dto.CreateArticleRequest;
import com.example.bcsd.article.dto.GetArticleResponse;
import com.example.bcsd.article.dto.GetArticlesResponse;
import com.example.bcsd.article.dto.UpdateArticleResponse;
import com.example.bcsd.article.repository.ArticleRepository;
import com.example.bcsd.member.model.Member;
import com.example.bcsd.member.repository.MemberRepository;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    public ArticleService(ArticleRepository articleRepository, MemberRepository memberRepository) {
        this.articleRepository = articleRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void CreateArticle(CreateArticleRequest request) {
        articleRepository.save(request.toArticle());
    }


    public GetArticlesResponse GetArticles() {
        List<Article> articles = articleRepository.findAll();
        List<Long> authorIds = articles.stream().map(Article::getAuthorId).distinct().toList();

        Map<Long, String> authorNames = new HashMap<>();
        for (Long id : authorIds) {
            Member member = memberRepository.findById(id);
            authorNames.put(id, member.getName());
        }

        List<GetArticleResponse> getArticleResponses = articles.stream().map(article -> {
            String authorName = authorNames.getOrDefault(article.getAuthorId(), "Unknown Author");
            return GetArticleResponse.from(article, authorName);
        }).toList();

        return new GetArticlesResponse(getArticleResponses, articles.size());
    }

    public GetArticlesResponse GetArticlesByBoardId(Long boardId) {
        List<Article> articles = articleRepository.findAll().stream().filter(article -> article.getBoardId().equals(boardId)).collect(Collectors.toList());
        List<Long> authorIds = articles.stream().map(Article::getAuthorId).distinct().toList();

        Map<Long, String> authorNames = new HashMap<>();
        for (Long id : authorIds) {
            Member member = memberRepository.findById(id);
            authorNames.put(id, member.getName());
        }

        List<GetArticleResponse> getArticleResponses = articles.stream().map(article -> {
            String authorName = authorNames.getOrDefault(article.getAuthorId(), "Unknown Author");
            return GetArticleResponse.from(article, authorName);
        }).toList();

        return new GetArticlesResponse(getArticleResponses, articles.size());
    }

    public GetArticlesResponse GetArticlesByMemberId(Long memberId) {
        List<Article> articles = articleRepository.findAll().stream().filter(article -> article.getAuthorId().equals(memberId)).collect(Collectors.toList());
        List<Long> authorIds = articles.stream().map(Article::getAuthorId).distinct().toList();

        Member member = memberRepository.findById(memberId);

        Map<Long, String> authorNames = new HashMap<>();
        for (Long id : authorIds) {
            authorNames.put(id, member.getName());
        }

        List<GetArticleResponse> getArticleResponses = articles.stream().map(article -> {
            String authorName = authorNames.getOrDefault(article.getAuthorId(), "Unknown Author");
            return GetArticleResponse.from(article, authorName);
        }).toList();

        return new GetArticlesResponse(getArticleResponses, articles.size());
    }

    public GetArticleResponse GetArticle(Long id) {
        Article article = articleRepository.findById(id);
        String author = memberRepository.findById(article.getAuthorId()).getName();

        return GetArticleResponse.from(article, author);
    }

    @Transactional
    public UpdateArticleResponse UpdateArticle(Long id, UpdateArticleRequest request) {
        Article article = articleRepository.findById(id);
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

        article.updateTitle(title);
        article.updateBoardId(boardId);
        article.updateContent(content);
        article.updateModifiedDate(LocalDateTime.now());

        articleRepository.updateSave(article);

        return UpdateArticleResponse.from(article);
    }

    @Transactional
    public void DeleteArticle(Long id) {
        articleRepository.delete(id);
    }
}
