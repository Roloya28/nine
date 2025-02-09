package com.example.nine.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity // <- 이걸 함으로써 DB에 쿼리가 나가게 된다
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public void update(String name) {
        this.name = name;
    }
}
