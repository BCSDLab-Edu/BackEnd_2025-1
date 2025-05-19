package com.example.bcsd.board.repository;

import com.example.bcsd.board.model.Board;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Board save(Board board) {
        String sql = "INSERT INTO board (name) VALUES (?)";
        jdbcTemplate.update(sql, board.getName());

        return board;
    }

    public Optional<Board> findById(Long id) {
        String sql = "SELECT * FROM board WHERE id = ?";
        try {
            Board board = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Board.class), id);

            return Optional.ofNullable(board);
        } catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
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
