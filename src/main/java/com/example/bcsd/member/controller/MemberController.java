package com.example.bcsd.member.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bcsd.member.dto.request.MemberCreateRequest;
import com.example.bcsd.member.dto.response.MemberResponse;
import com.example.bcsd.member.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberResponse> createMember(
        @RequestBody MemberCreateRequest request
    ) {
        MemberResponse response = memberService.createMember(request);
        return ResponseEntity.created(URI.create("/" + response.id())).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMember(
        @PathVariable(name = "id") Integer id
    ) {
        MemberResponse response = memberService.getMember(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getMembers() {
        List<MemberResponse> response = memberService.getMembers();
        return ResponseEntity.ok(response);
    }
}
