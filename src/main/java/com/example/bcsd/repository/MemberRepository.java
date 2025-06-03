package com.example.bcsd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bcsd.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(Long id);

    List<Member> findAll();

    void deleteById(Long id);

    Member save(Member member);
}
