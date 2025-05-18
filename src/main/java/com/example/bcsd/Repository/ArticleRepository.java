package com.example.bcsd.Repository;

import com.example.bcsd.Article;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ArticleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Article> findByBoardId(Long boardId) {
        String sql = "SELECT * FROM article WHERE board_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Article.class), boardId);
    }

    public Article findById(Long id) {
        String sql = "SELECT * FROM article WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Article.class), id);
    }

    public int save(Article article) {
        String sql = "INSERT INTO article (author_id, board_id, title, content, created_date, modified_date) " +
                "VALUES (?, ?, ?, ?, now(), now())";
        return jdbcTemplate.update(sql,
                article.getAuthorId(),
                article.getBoardId(),
                article.getTitle(),
                article.getContent());
    }

    public int update(Long id, Article article) {
        String sql = "UPDATE article SET title=?, content=?, modified_date=now() WHERE id=?";
        return jdbcTemplate.update(sql, article.getTitle(), article.getContent(), id);
    }

    public int delete(Long id) {
        String sql = "DELETE FROM article WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }
}
