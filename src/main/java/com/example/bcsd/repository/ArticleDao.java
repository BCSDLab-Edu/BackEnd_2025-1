package com.example.bcsd.repository;

import com.example.bcsd.domain.Article;
import com.example.bcsd.dto.ArticleResponseDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleDao {
    private final JdbcTemplate jdbc;

    public ArticleDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void insert(Article article) {
        String sql = "INSERT INTO article (author_id, board_id, title, content, created_date, modified_date) VALUES (?, ?, ?, ?, NOW(), NOW())";
        jdbc.update(sql, article.getAuthorId(), article.getBoardId(), article.getTitle(), article.getContent());
    }

    public List<Article> findAll() {
        return jdbc.query("SELECT * FROM article", new BeanPropertyRowMapper<>(Article.class));
    }

    public Article findById(Long id) {
        String sql = "SELECT * FROM article WHERE id = ?";
        return jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(Article.class), id);
    }

    public void update(Long id, Article article) {
        String sql = "UPDATE article SET board_id = ?, title = ?, content = ?, modified_date = NOW() WHERE id = ?";
        jdbc.update(sql, article.getBoardId(), article.getTitle(), article.getContent(), id);
    }

    public void delete(Long id) {
        jdbc.update("DELETE FROM article WHERE id = ?", id);
    }

    public List<ArticleResponseDto> findAllByBoardId(Long boardId) {
        String sql = "SELECT a.id, a.title, a.content, m.name AS authorName, b.name AS boardName, a.created_date, a.modified_date " +
                "FROM article a JOIN member m ON a.author_id = m.id JOIN board b ON a.board_id = b.id WHERE a.board_id = ?";

        return jdbc.query(sql, (rs, rowNum) -> new ArticleResponseDto(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getString("authorName"),
                rs.getString("boardName"),
                rs.getTimestamp("created_date").toLocalDateTime(),
                rs.getTimestamp("modified_date").toLocalDateTime()
        ), boardId);
    }

    public String getBoardNameById(Long boardId) {
        String sql = "SELECT name FROM board WHERE id = ?";
        List<String> result = jdbc.query(sql, (rs, rowNum) -> rs.getString("name"), boardId);
        return result.isEmpty() ? null : result.get(0);
    }

    public Article findLatest() {
        String sql = "SELECT * FROM article ORDER BY id DESC LIMIT 1";
        return jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(Article.class));
    }
}
