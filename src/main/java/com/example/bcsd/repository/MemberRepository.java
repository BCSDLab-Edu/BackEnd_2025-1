package com.example.bcsd.repository;

import com.example.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {
    private final List<Member> members = new ArrayList<>();

    public List<Member> findAll() {
        return members;
    }

    public Optional<Member> findById(Long id) {
        return members.stream().filter(m -> m.getId().equals(id)).findFirst();
    }

    public Member save(Member member) {
        members.add(member);
        return member;
    }

    public boolean deleteById(Long id) {
        return members.removeIf(m -> m.getId().equals(id));
    }
}