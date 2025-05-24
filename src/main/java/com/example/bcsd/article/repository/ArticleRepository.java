package com.example.bcsd.article.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.bcsd.article.model.Article;

@Repository
public class ArticleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ArticleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Article save(Article article) {
        String sql = "INSERT INTO article (author_id, board_id, title, content) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, article.getAuthorId(), article.getBoardId(), article.getTitle(), article.getContent());

        return article;
    }

    public Optional<Article> findById(Long id) {
        String sql = "SELECT * FROM article WHERE id = ?";
        try {
            Article article = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Article.class), id);

            return Optional.ofNullable(article);
        } catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Article> findAll() {
        String sql = "SELECT * FROM article";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Article.class));
    }

    public Article updateSave(Article article) {
        String sql = "UPDATE article SET board_id = ?, title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql, article.getBoardId(), article.getTitle(), article.getContent(), article.getId());

        return article;
    }

    public void delete(Long id) {
        String sql = "DELETE FROM article WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
