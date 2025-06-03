package com.example.bcsd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bcsd.dto.RequestDto.MemberCreateRequestDto;
import com.example.bcsd.dto.RequestDto.MemberUpdateRequestDto;
import com.example.bcsd.dto.ResponseDto.MemberResponseDto;
import com.example.bcsd.exception.DeletionNotAllowedException;
import com.example.bcsd.exception.EmailAlreadyExistsException;
import com.example.bcsd.exception.NotFoundException;
import com.example.bcsd.exception.NullRequestException;
import com.example.bcsd.model.Article;
import com.example.bcsd.model.Member;
import com.example.bcsd.repository.ArticleRepository;
import com.example.bcsd.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final ArticleRepository articleRepository;

    public MemberResponseDto getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("회원 못찾음"));
        return MemberResponseDto.from(member);
    }

    @Transactional
    public MemberResponseDto createMember(MemberCreateRequestDto requestDto) {
        List<Member> members = memberRepository.findAll();
        members.stream()
                .filter(m -> m.getEmail().equals(requestDto.getEmail()))
                .findFirst()
                .ifPresent(m -> {
                    throw new EmailAlreadyExistsException("이미 존재하는 이메일입니다.");
                });
        if (requestDto.getEmail() == null || requestDto.getPassword() == null || requestDto.getName() == null) {
            throw new NullRequestException("요청 값중에 null이 있습니다.");
        }
        Member member = Member.builder()
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .name(requestDto.getName())
                .build();
        memberRepository.save(member);
        return MemberResponseDto.from(member);
    }

    @Transactional
    public MemberResponseDto updateMember(Long memberId, MemberUpdateRequestDto requestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("회원 못찾음"));

        List<Member> members = memberRepository.findAll();

        members.stream()
                .filter(m -> m.getId() != memberId)
                .forEach(m -> {
                    if (m.getEmail().equals(requestDto.getEmail())) {
                        throw new EmailAlreadyExistsException("이미 존재하는 이메일입니다.");
                    }
                });
        member.setEmail(requestDto.getEmail());
        memberRepository.save(member);
        return MemberResponseDto.from(member);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("회원 못찾음"));

        List<Article> articles = articleRepository.findByAuthorId(memberId);
        if (!articles.isEmpty()) {
            throw new DeletionNotAllowedException("작성한 게시글이 존재하여 삭제할 수 없습니다.");
        }

        memberRepository.deleteById(memberId);
    }

}
