package com.example.bcsd.member.controller;

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

import com.example.bcsd.member.dto.GetMemberResponse;
import com.example.bcsd.member.dto.RegisterRequest;
import com.example.bcsd.member.dto.UpdateMemberRequest;
import com.example.bcsd.member.dto.UpdateMemberResponse;
import com.example.bcsd.member.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Void> CreateMember(
            @RequestBody RegisterRequest request
    ) {
        memberService.Register(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMemberResponse> GetMember(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok().body(GetMemberResponse.from(memberService.GetMember(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateMemberResponse> UpdateArticle(
            @PathVariable("id") Long id,
            @RequestBody UpdateMemberRequest request
    ) {
        UpdateMemberResponse res = memberService.UpdateMember(id, request);

        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteMember(
        @PathVariable("id") Long id
    ) {
        memberService.DeleteMember(id);
        
        return ResponseEntity.noContent().build();
    }
}
