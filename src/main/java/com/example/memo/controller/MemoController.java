package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memos")
    public MemoResponseDto save(@RequestBody MemoRequestDto dto) {
        return memoService.save(dto);
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> findAll() {
        return memoService.findAll();
    }

    @GetMapping("/memos/{id}")
    public MemoResponseDto findOne(@PathVariable Long id) {
        return memoService.findById(id);
    }

    @PutMapping("/memos/{id}")
    public MemoResponseDto update(@PathVariable Long id, @RequestBody MemoRequestDto dto) {
        return memoService.update(id, dto);
    }

    @DeleteMapping("/memos/{id}")
    public void delete(@PathVariable Long id) {
        memoService.deleteById(id);
    }

}
