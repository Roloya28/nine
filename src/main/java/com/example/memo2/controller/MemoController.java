package com.example.memo2.controller;

import com.example.memo2.dto.MemoRequestDto;
import com.example.memo2.dto.MemoResponseDto;
import com.example.memo2.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memos")
    public ResponseEntity<MemoResponseDto> save(@RequestBody MemoRequestDto dto) {
       return ResponseEntity.ok(memoService.save(dto));
    }

    @GetMapping("/memos")
    public ResponseEntity<List<MemoResponseDto>> findAll() {
        return ResponseEntity.ok(memoService.findAll());
    }

    @GetMapping("/memos/{id}")
    public ResponseEntity<MemoResponseDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(memoService.findById(id));
    }

    @PutMapping("/memos/{id}")
    public ResponseEntity<MemoResponseDto> update(@PathVariable Long id, @RequestBody MemoRequestDto dto) {
        return ResponseEntity.ok(memoService.update(id, dto));
    }

    @DeleteMapping("/memos/{id}")
    public void delete(@PathVariable Long id) {
        memoService.deleteById(id);
    }
}
