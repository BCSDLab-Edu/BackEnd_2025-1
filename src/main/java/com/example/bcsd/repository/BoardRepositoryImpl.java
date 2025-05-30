package com.example.bcsd.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BoardRepositoryImpl implements BoardRepository {
    /*
    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Board> boardRowMapper = (rs, rowNum) ->
            new Board(rs.getLong("id"), rs.getString("name"));

    public List<Board> findAll() {
        return jdbcTemplate.query("SELECT * FROM board", boardRowMapper);
    }

    public Optional<Board> findById(Long id) {
        List<Board> result = jdbcTemplate.query("SELECT * FROM board WHERE id = ?", boardRowMapper, id);
        return result.stream().findFirst();
    }



    public Optional<String> findNameById(Long id) {
        String sql = "SELECT name FROM board WHERE id = ?";
        List<String> results = jdbcTemplate.query(sql, new Object[]{id},
                (rs, rowNum) -> rs.getString("name"));

        return results.stream().findFirst();
    }

    public Board save(Board board) {
        jdbcTemplate.update("INSERT INTO board (id, name) VALUES (?, ?)", board.getId(), board.getName());
        return board;
    }

    public boolean deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM board WHERE id = ?", id) > 0;
    }
     */
}
