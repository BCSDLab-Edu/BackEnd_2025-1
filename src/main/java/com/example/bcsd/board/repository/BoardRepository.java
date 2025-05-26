package com.example.bcsd.board.repository;

import com.example.bcsd.board.model.Board;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardRepository {
    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Board save(Board board) {
        String sql = "INSERT INTO board (name) VALUES (?)";
        jdbcTemplate.update(sql, board.getName());

        return board;
    }

    public Board findById(Long id) {
        String sql = "SELECT * FROM board WHERE id = ?";
        Board board = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Board.class), id);

        return board;
    }

    public boolean boardExists(Long id) {
        String sql = "SELECT COUNT(*) FROM board WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);

        return count != null && count > 0;
    }

    public List<Board> findAll() {
        String sql = "SELECT * FROM board";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Board.class));
    }

    public Board updateSave(Board board) {
        String sql = "UPDATE board SET name = ? WHERE id = ?";
        jdbcTemplate.update(sql, board.getName(), board.getId());

        return board;
    }

    public void delete(Long id) {
        String sql = "DELETE FROM board WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
