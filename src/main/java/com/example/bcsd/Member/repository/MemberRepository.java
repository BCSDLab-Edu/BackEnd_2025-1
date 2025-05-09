package com.example.bcsd.article.repository;

import com.example.bcsd.article.model.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemberRepository {
    private final Map<Long, Member> memberDB = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Member save(Member member) {
        Long id = idCounter.getAndIncrement();
        member.setId(id);
        memberDB.put(id, member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(memberDB.get(id));
    }

    public List<Member> findAll() {
        return new ArrayList<>(memberDB.values());
    }

    public Member updateSave(Member member) {
        memberDB.put(member.getId(), member);
        return member;
    }

    public void delete(Long id) {
        memberDB.remove(id);
    }
}
