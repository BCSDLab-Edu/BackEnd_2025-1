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

    public Member findById(Long id) {
        String sql = "SELECT * FROM member WHERE id = ?";
        Member member = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Member.class), id);

        return member;
    }

    public Optional<Member> findByEmail(String email) {
        String sql = "SELECT * FROM member WHERE email = ?";
        try {
            Member member = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Member.class), email);
            
            return Optional.ofNullable(member);
        } catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public boolean memberExists(Long id) {
        String sql = "SELECT COUNT(*) FROM member WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);

        return count != null && count > 0;
    }

    public List<Member> findAll() {
        String sql = "SELECT * FROM member";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Member.class));
    }

    public Member updateSave(Member member) {
        if (member.getEmail() == null) {
            String sql = "UPDATE member SET name = ?, password = ? WHERE id = ?";
            jdbcTemplate.update(sql, member.getName(), MemberUtil.Pass2Hash(member.getPassword()), member.getId());
        } else {
            String sql = "UPDATE member SET name = ?, email = ?, password = ? WHERE id = ?";
            jdbcTemplate.update(sql, member.getName(), member.getEmail(), MemberUtil.Pass2Hash(member.getPassword()), member.getId());
        }

        return member;
    }

    public void delete(Long id) {
        String sql = "DELETE FROM member WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
