package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponseDto save(ScheduleRequestDto dto) { // Create
        Schedule schedule = new Schedule(dto.getTask(), dto.getAuthor(), dto.getPassword());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTask(), savedSchedule.getAuthor(), savedSchedule.getCreatedDate(), savedSchedule.getUpdatedDate());
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAll() { // Read All
        List<Schedule> schedules = scheduleRepository.findAll();

        List<ScheduleResponseDto> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            dtos.add(new ScheduleResponseDto(schedule.getId(), schedule.getTask(), schedule.getAuthor(), schedule.getCreatedDate(), schedule.getUpdatedDate()));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public ScheduleResponseDto findById(Long id) { // Read One
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 스케쥴이 없습니다.")
        );
        return new ScheduleResponseDto(schedule.getId(), schedule.getTask(), schedule.getAuthor(), schedule.getCreatedDate(), schedule.getUpdatedDate());
    }

    @Transactional
    public ScheduleResponseDto update(Long id, ScheduleRequestDto dto) { // Update
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 스케쥴이 없습니다.")
        );
        schedule.update(dto.getTask(), dto.getAuthor(), dto.getPassword());
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule.getId(), schedule.getTask(), schedule.getAuthor(), schedule.getCreatedDate(), schedule.getUpdatedDate());
    }

    public void deleteById(Long id) { // Delete
        if (!scheduleRepository.existsById(id)) {
            throw new IllegalArgumentException("스케쥴이 없습니다.");
        }
        scheduleRepository.deleteById(id);
    }
}
