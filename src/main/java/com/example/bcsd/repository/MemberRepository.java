package com.example.bcsd.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.bcsd.model.Member;

@Component
public class MemberRepository {
    private static Long DEFAULT_ID = 0L;
    private List<Member> members = new ArrayList<>(); // 데이터베이스 대용

    public MemberRepository() { // 초기 데이터
        save(new Member("name", "email", "pw"));
    }

    public Long getNextId() {
        return ++DEFAULT_ID;
    }

    public Optional<Member> findById(Long id) {
        return members.stream()
                .filter(member -> member.getId().equals(id))
                .findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(members); // 복제 리스트 반환(변경 방지)
    }

    public void deleteById(Long id) {
        members.removeIf(member -> member.getId().equals(id));
    }

    public void save(Member member) { // 저장(수정)
        if (member.getId() == null) {
            member.setId(getNextId()); // 생성
        } else {// 수정
            deleteById(member.getId());
        }
        members.add(member);
    }

}