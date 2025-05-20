package com.example.bcsd.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bcsd.member.dto.request.MemberCreateRequest;
import com.example.bcsd.member.dto.response.MemberResponse;
import com.example.bcsd.member.entity.Member;
import com.example.bcsd.member.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;

    public MemberService(MemberRepository memberRepository, MemberValidator memberValidator) {
        this.memberRepository = memberRepository;
        this.memberValidator = memberValidator;
    }

    @Transactional
    public MemberResponse createMember(MemberCreateRequest request) {
        memberValidator.validateEmail(request.email());
        memberValidator.validatePassword(request.password());
        memberValidator.validateName(request.name());

        memberRepository.findMemberByEmail(request.email())
            .ifPresent((member) -> {
                throw new IllegalStateException("등록된 이메일입니다.");
            });

        Member createMember = memberRepository.save(request.toMember());
        return MemberResponse.of(createMember.getId(), createMember.getName(), createMember.getEmail());
    }

    public MemberResponse getMember(Integer id) {
        Member member = memberRepository.findMemberById(id)
            .orElseThrow(() -> new IllegalStateException("등록되지 않은 멤버입니다."));

        return MemberResponse.of(member.getId(), member.getName(), member.getEmail());
    }

    public List<MemberResponse> getMembers() {
        List<Member> members = memberRepository.findMemberAll();
        return members.stream()
            .map(member -> MemberResponse.of(member.getId(), member.getName(), member.getEmail()))
            .toList();
    }
}
