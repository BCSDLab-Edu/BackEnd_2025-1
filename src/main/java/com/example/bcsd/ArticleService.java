package com.example.bcsd;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDateTime;

@Service
public class ArticleService {
    static List<Article> articleList = new ArrayList<>();
    private static int currentId = 1;
    private static LocalDateTime now = LocalDateTime.now();

    static void updateTime() {
        now = LocalDateTime.now();
    }

    // GET /articles/{id}
    public Article getArticle(int id) {
        for (Article article : articleList) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    // POST /articles
    public Article createArticle(Article article) {
        updateTime();
        article.setId(currentId++);
        article.setMemberId(0);
        article.setBoardId(0);
        article.setCreatedDate(now);
        article.setEditedDate(now);
        articleList.add(article);
        return article;
    }

    // PUT /articles/{id}
    public Article updateArticle(int id, Article updatedArticle) {
        for (Article article : articleList) {
            if (article.getId() == id) {
                updateTime();
                article.setBoardId(updatedArticle.getBoardId());
                article.setTitle(updatedArticle.getTitle());
                article.setAuthor(updatedArticle.getAuthor());
                article.setContent(updatedArticle.getContent());
                article.setEditedDate(now);
                return article;
            }
        }
        return null;
    }

    // DELETE /articles/{id}
    public boolean deleteArticle(int id) {
        Iterator<Article> iterator = articleList.iterator();
        while (iterator.hasNext()) {
            Article article = iterator.next();
            if (article.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    // GET /articles
    public List<Article> viewArticles() {
        return articleList;
    }
}