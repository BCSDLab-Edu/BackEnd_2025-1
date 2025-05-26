package com.example.bcsd.dao;

import com.example.bcsd.domain.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleDao {

    private final JdbcTemplate jdbcTemplate;

    public ArticleDao(JdbcTemplate jdbcTemplate) {
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

    public List<Article> findByBoardId(Long boardId) {
        String sql = "SELECT * FROM article WHERE board_id = ? ORDER BY created_date DESC";
        return jdbcTemplate.query(sql, articleRowMapper, boardId);
    }

    public Optional<Article> findById(Long id) {
        String sql = "SELECT * FROM article WHERE id = ?";
        return jdbcTemplate.query(sql, articleRowMapper, id).stream().findFirst();
    }

    public Article save(Article article) {
        String sql = """
                INSERT INTO article (author_id, board_id, title, content, created_date, modified_date)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(sql,
                article.getAuthorId(),
                article.getBoardId(),
                article.getTitle(),
                article.getContent(),
                Timestamp.valueOf(article.getCreatedDate()),
                Timestamp.valueOf(article.getModifiedDate())
        );

        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return new Article(
                id,
                article.getAuthorId(),
                article.getBoardId(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedDate(),
                article.getModifiedDate()
        );
    }

    public void update(Long id, Article updated) {
        String sql = """
                UPDATE article
                SET title = ?, content = ?, board_id = ?, modified_date = ?
                WHERE id = ?
                """;
        jdbcTemplate.update(sql,
                updated.getTitle(),
                updated.getContent(),
                updated.getBoardId(),
                Timestamp.valueOf(updated.getModifiedDate()),
                id
        );
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM article WHERE id = ?", id);
    }
}
