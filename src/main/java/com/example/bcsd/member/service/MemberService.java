package com.example.bcsd.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bcsd.member.model.Member;
import com.example.bcsd.member.dto.RegisterRequest;
import com.example.bcsd.member.dto.UpdateMemberRequest;
import com.example.bcsd.member.dto.UpdateMemberResponse;
import com.example.bcsd.member.repository.MemberRepository;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void Register(RegisterRequest request) {
        memberRepository.save(request.toMember());
    }

    public Member GetMember(Long id) {
        return memberRepository.findById(id);
    }

    @Transactional
    public UpdateMemberResponse UpdateMember(Long id, UpdateMemberRequest request) {
        Member member = GetMember(id);
        if (request.name() != null) {
            member.setName(request.name());
        }

        if (request.email() != null) {
            member.setEmail(request.email());
        } else {
            member.setEmail(null);
        }

        memberRepository.updateSave(member);

        return UpdateMemberResponse.from(member);
    }

    @Transactional
    public void DeleteMember(Long id) {
        memberRepository.delete(id);
    }
}
