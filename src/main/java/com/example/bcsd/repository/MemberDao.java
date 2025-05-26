package com.example.bcsd.repository;

import com.example.bcsd.domain.Member;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDao {
    private final JdbcTemplate jdbc;

    public MemberDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void insert(Member member) {
        String sql = "INSERT INTO member (name, email, password) VALUES (?, ?, ?)";
        jdbc.update(sql, member.getName(), member.getEmail(), member.getPassword());
    }

    public List<Member> findAll() {
        String sql = "SELECT * FROM member";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(Member.class));
    }

    public Member findById(Long id) {
        String sql = "SELECT * FROM member WHERE id = ?";
        return jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(Member.class), id);
    }

    public void update(Long id, Member member) {
        String sql = "UPDATE member SET name = ?, email = ?, password = ? WHERE id = ?";
        jdbc.update(sql, member.getName(), member.getEmail(), member.getPassword(), id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM member WHERE id = ?";
        jdbc.update(sql, id);
    }
}
