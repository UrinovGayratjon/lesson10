package com.example.lesson10_task_1.uz.urinov.controller;

import com.example.lesson10_task_1.uz.urinov.payload.ApiResponse;
import com.example.lesson10_task_1.uz.urinov.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/hotel")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @GetMapping("/")
    public HttpEntity<?> getAllHotels() {
        ApiResponse apiResponse = hotelService.getAllHotels();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAllHotel(@PathVariable Integer id) {
        ApiResponse apiResponse = hotelService.getHotelById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }


    @PostMapping("/")
    public HttpEntity<?> addHotel(@RequestBody String name) {
        ApiResponse apiResponse = hotelService.addHotel(name);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editHotel(@PathVariable Integer id, @RequestBody String name) {
        ApiResponse apiResponse = hotelService.editHotel(id, name);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteHotel(@PathVariable Integer id) {
        ApiResponse apiResponse = hotelService.deleteHotelById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }
}
