package com.example.bcsd.repository;

import com.example.bcsd.domain.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ArticleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Article> articleRowMapper = (rs, rowNum) -> new Article(
            rs.getLong("id"),
            rs.getLong("writer_id"),
            rs.getLong("board_id"),
            rs.getString("title"),
            rs.getString("content"),
            rs.getTimestamp("created_date").toLocalDateTime(),
            rs.getTimestamp("modified_date").toLocalDateTime()
    );

    public List<Article> findAll() {
        String sql = "SELECT * FROM article";
        return jdbcTemplate.query(sql, articleRowMapper);
    }

    public List<Article> findByBoardId(Long boardId) {
        String sql = "SELECT * FROM article WHERE board_id = ?";
        return jdbcTemplate.query(sql, articleRowMapper, boardId);
    }

    public Optional<Article> findById(Long id) {
        String sql = "SELECT * FROM article WHERE id = ?";
        List<Article> results = jdbcTemplate.query(sql, articleRowMapper, id);
        return results.stream().findFirst();
    }

    public Article save(Article article) {
        String sql = "INSERT INTO article (id, writer_id, board_id, title, content, created_date, modified_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                article.getId(),
                article.getWriterId(),
                article.getBoardId(),
                article.getTitle(),
                article.getContent(),
                Timestamp.valueOf(article.getCreatedDate()),
                Timestamp.valueOf(article.getModifiedDate())
        );
        return article;
    }

    public void update(Article article) {
        String sql = "UPDATE article SET title = ?, content = ?, modified_date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                article.getTitle(),
                article.getContent(),
                Timestamp.valueOf(article.getModifiedDate()),
                article.getId()
        );
    }


    public boolean deleteById(Long id) {
        String sql = "DELETE FROM article WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}