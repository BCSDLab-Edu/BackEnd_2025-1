package com.example.bcsd.repository;

import com.example.bcsd.domain.Board;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDao {
    private final JdbcTemplate jdbc;

    public BoardDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void insert(Board board) {
        jdbc.update("INSERT INTO board (name) VALUES (?)", board.getName());
    }

    public List<Board> findAll() {
        return jdbc.query("SELECT * FROM board", new BeanPropertyRowMapper<>(Board.class));
    }

    public Board findById(Long id) {
        return jdbc.queryForObject("SELECT * FROM board WHERE id = ?", new BeanPropertyRowMapper<>(Board.class), id);
    }

    public void update(Long id, Board board) {
        jdbc.update("UPDATE board SET name = ? WHERE id = ?", board.getName(), id);
    }

    public void delete(Long id) {
        jdbc.update("DELETE FROM board WHERE id = ?", id);
    }
}
