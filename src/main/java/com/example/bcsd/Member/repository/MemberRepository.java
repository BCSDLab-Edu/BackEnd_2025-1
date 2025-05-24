package com.example.bcsd.member.repository;

import com.example.bcsd.member.model.Member;
import com.example.bcsd.member.util.MemberUtil;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Member save(Member member) {
        String sql = "INSERT INTO member (name, email, password) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, member.getName(), member.getEmail(), MemberUtil.Pass2Hash( member.getPassword()));

        return member;
    }

    public Optional<Member> findById(Long id) {
        String sql = "SELECT * FROM member WHERE id = ?";
        try {
            Member member = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Member.class), id);

            return Optional.ofNullable(member);
        } catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Member> findAll() {
        String sql = "SELECT * FROM member";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Member.class));
    }

    public Member updateSave(Member member) {
        String sql = "UPDATE member SET name = ?, email = ?, password = ?";
        jdbcTemplate.update(sql, member.getName(), member.getEmail(), MemberUtil.Pass2Hash(member.getPassword()));

        return member;
    }

    public void delete(Long id) {
        String sql = "DELETE FROM member WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
