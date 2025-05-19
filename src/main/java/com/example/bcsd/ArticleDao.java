package com.example.bcsd;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ArticleDao {
    private final JdbcTemplate jdbc;

    public ArticleDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<ArticleDto> findAllByBoardId(Long boardId) {
        String sql = "SELECT a.id, a.title, a.content, m.name AS authorName, b.name AS boardName, a.created_date, a.modified_date " +
                "FROM article a " +
                "JOIN member m ON a.author_id = m.id " +
                "JOIN board b ON a.board_id = b.id " +
                "WHERE a.board_id = ?";

        return jdbc.query(sql, (rs, rowNum) -> new ArticleDto(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getString("authorName"),
                rs.getString("boardName"),
                rs.getTimestamp("created_date").toLocalDateTime(),
                rs.getTimestamp("modified_date").toLocalDateTime()
        ), boardId);
    }
}