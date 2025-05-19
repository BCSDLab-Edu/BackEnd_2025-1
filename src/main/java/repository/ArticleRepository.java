package com.example.bcsd.repository;

import com.example.bcsd.model.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ArticleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Article> articleRowMapper = (rs, rowNum) -> new Article(
            rs.getLong("id"),
            rs.getLong("author_id"),
            rs.getLong("board_id"),
            rs.getString("title"),
            rs.getString("content"),
            rs.getTimestamp("created_date").toLocalDateTime(),
            rs.getTimestamp("modified_date").toLocalDateTime()
    );

    public List<Article> findAll() {
        return jdbcTemplate.query("SELECT * FROM article", articleRowMapper);
    }

    public List<Article> findByBoardId(Long boardId) {
        return jdbcTemplate.query("SELECT * FROM article WHERE board_id = ?", articleRowMapper, boardId);
    }

    public Article findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM article WHERE id = ?", articleRowMapper, id);
    }

    public Article save(Article article) {
        LocalDateTime now = LocalDateTime.now();
        jdbcTemplate.update(
                "INSERT INTO article (author_id, board_id, title, content, created_date, modified_date) VALUES (?, ?, ?, ?, ?, ?)",
                article.getAuthorId(), article.getBoardId(), article.getTitle(), article.getContent(),
                Timestamp.valueOf(now), Timestamp.valueOf(now)
        );
        return article;
    }

    public int update(Long id, Article updated) {
        return jdbcTemplate.update(
                "UPDATE article SET title = ?, content = ?, modified_date = ? WHERE id = ?",
                updated.getTitle(), updated.getContent(), Timestamp.valueOf(LocalDateTime.now()), id
        );
    }

    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM article WHERE id = ?", id);
    }
}
