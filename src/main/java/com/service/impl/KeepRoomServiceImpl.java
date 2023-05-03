package com.service.impl;

import com.dto.KeepRoomDto;
import com.model.KeepRoom;
import com.repository.KeepRoomRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class KeepRoomServiceImpl implements com.service.KeepRoomService {
    private final KeepRoomRepo keepRoomRepo;

    @Override
    public KeepRoomDto create(KeepRoom keepRoom) {
        keepRoomRepo.save(keepRoom);
        return KeepRoomDto.toKeepRoomDto(keepRoom);
    }

    @Override
    public List<KeepRoomDto> getAllKeepRooms() {
        return keepRoomRepo.findAll().stream().map(KeepRoomDto::toKeepRoomDto).collect(Collectors.toList());
    }

    @Override
    public KeepRoomDto update(Long id, KeepRoom keepRoom) {
        KeepRoom keepRoomUpdated = keepRoomRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id - " + id + " not found"));
        return KeepRoomDto.toKeepRoomDto(keepRoomUpdated);
    }

    @Override
    public void delete(Long id) {
        KeepRoom keepRoom = keepRoomRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id - " + id + " not found"));
        keepRoomRepo.delete(keepRoom);
    }

    @Override
    public KeepRoomDto getKeepRooms(Long id) {
        KeepRoom keepRoom = keepRoomRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id - " + id + " not found"));
        return KeepRoomDto.toKeepRoomDto(keepRoom);
    }
}
