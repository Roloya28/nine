package com.example.nine.controller;

import com.example.nine.dto.MemberRequestDto;
import com.example.nine.dto.MemberResponseDto;
import com.example.nine.repository.MemberRepository;
import com.example.nine.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// JSON 방식으로 통신할거라서 REST api 사용
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/members")
    public ResponseEntity<MemberResponseDto> save(@RequestBody MemberRequestDto dto) {
        return ResponseEntity.ok(memberService.save(dto));
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponseDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<MemberResponseDto> update(
            @PathVariable Long id,
            @RequestBody MemberRequestDto dto
    ) {
        return ResponseEntity.ok(memberService.update(id, dto));
    }

    @DeleteMapping("/members/{id}")
    public void delete(@PathVariable Long id) {
        memberService.deleteById(id);
    }
}
