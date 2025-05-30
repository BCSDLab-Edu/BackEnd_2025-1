package com.example.bcsd.repository;


import com.example.bcsd.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    List<Member> findAll();
    Optional<Member> findById(Long id);
    Optional<Member> findByEmail(String email);
    Member save(Member member);
    void update(Member member, Long id);
    boolean deleteById(Long id);
}