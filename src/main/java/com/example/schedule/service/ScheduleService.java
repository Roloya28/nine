package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequesetDto;
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

    private  final ScheduleRepository scheduleRepository;



    @Transactional
    public ScheduleResponseDto save(ScheduleRequesetDto dto) {
        Schedule schedule = new Schedule(dto.getContent());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getContent());
    }

    public List<ScheduleResponseDto> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();

        List<ScheduleResponseDto> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            dtos.add(new ScheduleResponseDto(schedule.getId(), schedule.getContent()));
        }
        return dtos;
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 스케쥴이 없습니다.")
        );
        return new ScheduleResponseDto(schedule.getId(), schedule.getContent());
    }

    public ScheduleResponseDto update(Long id, ScheduleRequesetDto dto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 스케쥴이 없습니다.")
        );
        schedule.update(dto.getContent());
        return new ScheduleResponseDto(schedule.getId(), schedule.getContent());
    }

    public void deleteById(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new IllegalArgumentException("없습니다.");
        }
        scheduleRepository.deleteById(id);
    }

}
