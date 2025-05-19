package com.example.bcsd.repository;

import com.example.bcsd.model.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Board> findById(Long id) {
        List<Board> results = jdbcTemplate.query("SELECT * FROM board WHERE id = ?",
                (rs, rowNum) -> new Board(
                        rs.getLong("id"),
                        rs.getString("name")
                ),
                id);
        return results.stream().findFirst();
    }

    public List<Board> findAll() {
        return jdbcTemplate.query("SELECT * FROM board",
                (rs, rowNum) -> new Board(
                        rs.getLong("id"),
                        rs.getString("name")
                ));
    }
}
