package com.example.bcsd.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bcsd.member.dto.request.MemberCreateRequest;
import com.example.bcsd.member.dto.response.MemberResponse;
import com.example.bcsd.member.entity.Member;
import com.example.bcsd.member.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberResponse createMember(MemberCreateRequest request) {
        memberRepository.findMemberByEmail(request.email())
            .ifPresent((member) -> {
                throw new IllegalStateException("등록된 이메일입니다.");
            });

        Member createMember = memberRepository.save(request.toMember());
        return MemberResponse.of(createMember.getId(), createMember.getName(), createMember.getEmail());
    }
}
