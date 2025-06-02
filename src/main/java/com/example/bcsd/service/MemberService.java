package com.example.bcsd.service;

import com.example.bcsd.domain.Member;
import com.example.bcsd.dto.MemberRequestDto;
import com.example.bcsd.dto.MemberResponseDto;
import com.example.bcsd.repository.MemberDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final MemberDao memberDao;

    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Transactional
    public void create(MemberRequestDto dto) {
        Member member = new Member(null, dto.getName(), dto.getEmail(), dto.getPassword());
        memberDao.insert(member);
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> getAll() {
        return memberDao.findAll().stream()
                .map(m -> new MemberResponseDto(m.getId(), m.getName(), m.getEmail()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MemberResponseDto getById(Long id) {
        Member m = memberDao.findById(id);
        return new MemberResponseDto(m.getId(), m.getName(), m.getEmail());
    }

    @Transactional
    public void update(Long id, MemberRequestDto dto) {
        Member member = new Member(id, dto.getName(), dto.getEmail(), dto.getPassword());
        memberDao.update(id, member);
    }

    @Transactional
    public void delete(Long id) {
        memberDao.delete(id);
    }
}
