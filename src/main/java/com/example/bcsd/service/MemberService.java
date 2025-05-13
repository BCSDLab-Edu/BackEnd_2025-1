package com.example.bcsd.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.dto.MemberRequestDto;
import com.example.hellospring.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new NullPointerException(""));
    }

    public Member createMember(MemberRequestDto dto) {
        Member member = new Member(dto.getId(), dto.getName(), dto.getEmail(), dto.getPassword());
        return memberRepository.save(member);
    }

    public void deleteMember(Long id) {
        if (!memberRepository.deleteById(id)) {
            throw new NullPointerException("");
        }
    }
}