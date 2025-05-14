package com.example.bcsd.repository;

import com.example.bcsd.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Member> memberRowMapper = (rs, rowNum) ->
            new Member(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password")
            );

    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT * FROM member", memberRowMapper);
    }

    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("SELECT * FROM member WHERE id = ?", memberRowMapper, id);
        return result.stream().findFirst();
    }

    public Member save(Member member) {
        jdbcTemplate.update("INSERT INTO member (id, name, email, password) VALUES (?, ?, ?, ?)",
                member.getId(), member.getName(), member.getEmail(), member.getPassword());
        return member;
    }

    public boolean deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM member WHERE id = ?", id) > 0;
    }
}