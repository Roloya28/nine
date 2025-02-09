package com.example.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private final Long id; // DB 에서 자동 생성
    private final String task;
    private final String author;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;

    public ScheduleResponseDto(Long id, String task, String author, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.task = task;
        this.author = author;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
