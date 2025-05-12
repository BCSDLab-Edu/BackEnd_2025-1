package com.example.bcsd.repository;

import com.example.bcsd.model.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
    private Long key = 1L;
    private final Map<Long, Member> members = new HashMap<>();

    public List<Member> findAll() {
        List<Member> result = new ArrayList<>();

        members.forEach((id, member) ->
                result.add(member));

        return result;
    }

    public Optional<Member> findById(Long id) {
        Member member = members.get(id);

        return Optional.ofNullable(member);
    }

    public Member save(Member member) {
        member.setId(key);
        members.put(key++, member);

        return member;
    }

    public Member save(Long id, Member member) {
        members.put(id, member);

        return member;
    }

    public void delete(Long id) {
        members.remove(id);
    }
}
