package com.controller;

import com.dto.KeepRoomDto;
import com.model.KeepRoom;
import com.service.KeepRoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/rooms")
@AllArgsConstructor
public class KeepRoomController {
    private final KeepRoomService keepRoomService;

    @GetMapping("/all")
    ResponseEntity<List<KeepRoomDto>> getAllKeepRooms() {
        List<KeepRoomDto> orders = keepRoomService.getAllKeepRooms();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    ResponseEntity<KeepRoomDto> getRoomByID(@PathVariable Long id) {
        return new ResponseEntity<>(keepRoomService.getKeepRooms(id), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<KeepRoomDto> createKeepRoom(@RequestBody KeepRoom keepRoom) {
        return ResponseEntity.status(HttpStatus.CREATED).body(keepRoomService.create(keepRoom));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<KeepRoomDto> updateKeepRoom(@PathVariable Long id, @RequestBody KeepRoom keepRoom) {
        return ResponseEntity.status(HttpStatus.CREATED).body(keepRoomService.update(id, keepRoom));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOKeepRoom(@PathVariable Long id) {
        keepRoomService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
