package com.example.bcsd.repository;

import com.example.bcsd.model.Article;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ArticleRepository {

    private Map<Long, Article> articleStore = new HashMap<>();
    private Long currentId = 1L;

    public Article save(Article article) {
        article.setId(currentId++);
        articleStore.put(article.getId(), article);
        return article;
    }

    public Collection<Article> findAll() {
        return articleStore.values();
    }

    public Article findById(Long id) {
        return articleStore.get(id);
    }

    public Article update(Long id, Article updatedArticle) {
        Article existingArticle = articleStore.get(id);
        if (existingArticle != null) {
            existingArticle.setTitle(updatedArticle.getTitle());
            existingArticle.setContent(updatedArticle.getContent());
            existingArticle.setAuthor(updatedArticle.getAuthor());
            return existingArticle;
        }
        return null;
    }

    public String delete(Long id) {
        Article removedArticle = articleStore.remove(id);
        if (removedArticle != null) {
            return "삭제 완료!";
        }
        return "게시글을 찾을 수 없습니다.";
    }
}
