package com.example.bcsd.service;

import com.example.bcsd.domain.Member;
import com.example.bcsd.dto.MemberRequestDto;
import com.example.bcsd.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Member createMember(MemberRequestDto dto) {
        Member member = new Member(dto.getId(), dto.getName(), dto.getEmail(), dto.getPassword());
        return memberRepository.save(member);
    }

    @Transactional
    public Member updateMember(MemberRequestDto dto) {
        Member member = memberRepository.findById(dto.getId());
    }

    @Transactional
    public void deleteMember(Long id) {
        if (!memberRepository.deleteById(id)) {
            throw new NullPointerException("");
        }
    }
}