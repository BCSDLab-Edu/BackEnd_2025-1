package com.example.bcsd.controller;

import com.example.bcsd.domain.Member;
import com.example.bcsd.dto.MemberRequestDto;
import com.example.bcsd.dto.MemberResponseDto;
import com.example.bcsd.dto.MemberUpdateRequestDto;
import com.example.bcsd.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public MemberResponseDto getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @PostMapping
    public MemberResponseDto createMember(@RequestBody @Valid MemberRequestDto dto) {
        return memberService.createMember(dto);
    }

    @PutMapping("/{id}")
    public MemberResponseDto updateMember(@PathVariable Long id, @RequestBody MemberUpdateRequestDto dto) {
        return memberService.updateMember(dto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}