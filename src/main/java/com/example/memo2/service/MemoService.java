package com.example.memo2.service;

import com.example.memo2.dto.MemoRequestDto;
import com.example.memo2.dto.MemoResponseDto;
import com.example.memo2.entity.Memo;
import com.example.memo2.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponseDto save(MemoRequestDto dto) {
        Memo memo= new Memo(dto.getContent());
        Memo savedMemo = memoRepository.save(memo);
        return new MemoResponseDto(savedMemo.getId(), savedMemo.getContent());
    }

    public List<MemoResponseDto> findAll() {
        List<Memo> memos = memoRepository.findAll();

        List<MemoResponseDto> dtos = new ArrayList<>();
        for (Memo memo : memos) {
            dtos.add(new MemoResponseDto(memo.getId(), memo.getContent()));
        }
        return dtos;
    }

    public MemoResponseDto findById(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 메모가 없습니다.")
        );
        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    public MemoResponseDto update(Long id, MemoRequestDto dto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 메모가 없습니다.")
        );

        memo.update(dto.getContent());
        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    public void deleteById(Long id) {
        if (!memoRepository.existsById(id)) {
            throw new IllegalArgumentException("없음 ㅅㄱ");
        }
        memoRepository.deleteById(id);
    }
}
