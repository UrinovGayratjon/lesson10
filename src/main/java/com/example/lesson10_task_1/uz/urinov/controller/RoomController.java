package com.example.lesson10_task_1.uz.urinov.controller;

import com.example.lesson10_task_1.uz.urinov.entity.Room;
import com.example.lesson10_task_1.uz.urinov.payload.ApiResponse;
import com.example.lesson10_task_1.uz.urinov.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/room")
public class RoomController {


    @Autowired
    RoomService roomService;

    @GetMapping("/{hotelId}")
    public HttpEntity<?> getAllRoomsByHotelId(@PathVariable Integer hotelId, @RequestBody Integer page) {
        ApiResponse apiResponse = roomService.getAllRoomsByHotelId(hotelId, page);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @GetMapping("/")
    public HttpEntity<?> getAllRooms() {
        ApiResponse apiResponse = roomService.getAllRooms();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAllRoom(@PathVariable Integer id) {
        ApiResponse apiResponse = roomService.getRoomById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }


    @PostMapping("/{continentId}")
    public HttpEntity<?> addRoom(@PathVariable Integer continentId, @RequestBody Room room) {
        ApiResponse apiResponse = roomService.addRoom(continentId, room);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editRoom(@PathVariable Integer id, @RequestBody Room room) {
        ApiResponse apiResponse = roomService.editRoom(id, room);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteRoom(@PathVariable Integer id) {
        ApiResponse apiResponse = roomService.deleteRoomById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    
}
