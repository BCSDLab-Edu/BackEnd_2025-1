package com.example.bcsd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bcsd.dto.RequestDto.MemberCreateRequestDto;
import com.example.bcsd.dto.RequestDto.MemberUpdateRequestDto;
import com.example.bcsd.dto.ResponseDto.MemberResponseDto;
import com.example.bcsd.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    
    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable(name = "memberId") Long memberId) {
        MemberResponseDto responseDto = memberService.getMember(memberId);
        return ResponseEntity.ok().body(responseDto);
    }

    @PostMapping
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberCreateRequestDto requestDto) {
        MemberResponseDto responseDto = memberService.createMember(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> updateMember(@PathVariable(name = "memberId") Long memberId,@RequestBody MemberUpdateRequestDto requestDto) {
        MemberResponseDto responseDto = memberService.updateMember(memberId,requestDto);
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable(name = "memberId") Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }
}
