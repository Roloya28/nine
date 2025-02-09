package com.example.nine.service;

import com.example.nine.dto.MemberRequestDto;
import com.example.nine.dto.MemberResponseDto;
import com.example.nine.entity.Member;
import com.example.nine.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto save(MemberRequestDto dto) {
        Member member = new Member(dto.getName());
        Member savedMember = memberRepository.save(member);

        return new MemberResponseDto(savedMember.getId(), savedMember.getName());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();

        List<MemberResponseDto> dtos = new ArrayList<>();
        for (Member member : members) {
            MemberResponseDto dto = new MemberResponseDto(member.getId(), member.getName());
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그 id 회원 없음! 안줘!")
        );

        return new MemberResponseDto(member.getId(), member.getName());
    }

    @Transactional
    public MemberResponseDto update(Long id, MemberRequestDto dto) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그 id 회원 없음! 안줘!")
        );

        // 여기서 업데이트가 된것. 더 편해짐
        member.update(dto.getName()); // 영속성 컨텍스트?
        return new MemberResponseDto(member.getId(), member.getName());
    }

    @Transactional
    public void deleteById(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new IllegalArgumentException("그런 Id 가진 회원 없음. 삭제 못함");
        }

        memberRepository.deleteById(id);
    }
}
