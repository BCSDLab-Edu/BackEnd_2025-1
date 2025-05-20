package com.example.bcsd.member.repository;

import java.util.List;
import java.util.Optional;

import com.example.bcsd.member.entity.Member;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findMemberByEmail(String email);
    Optional<Member> findMemberById(Integer id);
    List<Member> findMemberAll();
}
