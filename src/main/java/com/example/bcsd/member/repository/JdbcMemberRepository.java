package com.example.bcsd.member.repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.bcsd.member.entity.Member;

@Repository
public class JdbcMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;
    private final KeyHolder keyHolder = new GeneratedKeyHolder();

    public JdbcMemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Member save(Member member) {
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement("""
                INSERT INTO member (name, email, password) VALUES (?, ?, ?)
                """, new String[]{"id"});
            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setString(3, member.getPassword());
            return ps;
        }, keyHolder);
        return findMemberById(keyHolder.getKey().intValue()).get();
    }

    @Override
    public Optional<Member> findMemberByEmail(String email) {
        String sql = "SELECT id, name, email, password FROM member WHERE email = ?";
        List<Member> result = jdbcTemplate.query(sql, memberRowMapper, email);
        return result.stream().findFirst();
    }

    @Override
    public Optional<Member> findMemberById(Integer id) {
        String sql = "SELECT id, name, email, password FROM member WHERE id = ?";
        List<Member> result = jdbcTemplate.query(sql, memberRowMapper, id);
        return result.stream().findFirst();
    }

    @Override
    public List<Member> findMemberAll() {
        String sql = "SELECT id, name, email, password FROM member";
        return jdbcTemplate.query(sql, memberRowMapper);
    }

    private static final RowMapper<Member> memberRowMapper = (rs, rowNum) -> new Member(
        rs.getInt("id"),
        rs.getString("name"),
        rs.getString("email"),
        rs.getString("password")
    );
}
