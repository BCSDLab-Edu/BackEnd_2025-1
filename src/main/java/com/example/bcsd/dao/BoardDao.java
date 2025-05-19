package com.example.bcsd.dao;

import com.example.bcsd.domain.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BoardDao {

    private final JdbcTemplate jdbcTemplate;

    public BoardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Board> boardRowMapper = (rs, rowNum) ->
            new Board(rs.getLong("id"), rs.getString("name"));

    public Optional<Board> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM board WHERE id = ?", boardRowMapper, id)
                .stream().findFirst();
    }

    public Board save(Board board) {
        jdbcTemplate.update("INSERT INTO board (name) VALUES (?)", board.name());
        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return new Board(id, board.name());
    }
}
