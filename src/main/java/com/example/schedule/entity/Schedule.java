package com.example.schedule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // DB 에서 자동 생성
    private String task;
    private String author;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Schedule(String task, String author, String password) {
        this.task = task;
        this.author = author;
        this.password = password;
    }

    public Schedule(Long id, String task, String author, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.task = task;
        this.author = author;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;

    }

    public void update(String task, String author, String password) {
        this.task = task;
        this.author = author;
        this.password = password;
    }
}