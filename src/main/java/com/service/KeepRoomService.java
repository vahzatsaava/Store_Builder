package com.service;

import com.dto.KeepRoomDto;
import com.model.KeepRoom;

import java.util.List;

public interface KeepRoomService {
    KeepRoomDto create(KeepRoom keepRoom);

    List<KeepRoomDto> getAllKeepRooms();

    KeepRoomDto update(Long id,KeepRoom keepRoom);

    void delete(Long id);

    KeepRoomDto getKeepRooms(Long id);
}
