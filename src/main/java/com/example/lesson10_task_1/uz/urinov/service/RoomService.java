package com.example.lesson10_task_1.uz.urinov.service;

import com.example.lesson10_task_1.uz.urinov.entity.Hotel;
import com.example.lesson10_task_1.uz.urinov.entity.Room;
import com.example.lesson10_task_1.uz.urinov.payload.ApiResponse;
import com.example.lesson10_task_1.uz.urinov.repository.HotelRepository;
import com.example.lesson10_task_1.uz.urinov.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Optional;

@Service
public class RoomService {


    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;

    public ApiResponse getAllRoomsByHotelId(Integer hotelId, int page) {
        Pageable pageable = (Pageable) PageRequest.of(page, 20);
        return new ApiResponse("Successfully implemented", true, roomRepository.findAllByHotel_Id(hotelId, pageable));
    }

    public ApiResponse getAllRooms() {
        return new ApiResponse("Successfully implemented", true, roomRepository.findAll());
    }

    public ApiResponse getRoomById(Integer id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        return optionalRoom.map(room -> new ApiResponse("Successfully implemented", true, room)).orElseGet(() -> new ApiResponse("Not found", false));
    }


    public ApiResponse addRoom(Integer hotelId, Room room) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (!optionalHotel.isPresent())
            return new ApiResponse("Not found.", false);

        Hotel hotel = optionalHotel.get();

        String number = room.getNumber();
        Integer floor = room.getFloor();
        Integer size = room.getSize();
        if (roomRepository.existsByNumberAndFloorAndHotel(number, floor, hotel))
            return new ApiResponse("This name already exists in Room", false);

        Room newRoom = new Room();
        newRoom.setNumber(number);
        newRoom.setFloor(floor);
        newRoom.setSize(size);
        newRoom.setHotel(hotel);
        Room saveRoom = roomRepository.save(newRoom);
        return new ApiResponse("Successfully implemented", true, saveRoom);
    }


    public ApiResponse editRoom(Integer hotelId, Room room) {

        Optional<Room> optionalRoom = roomRepository.findById(hotelId);

        if (!optionalRoom.isPresent())
            return new ApiResponse("Not found.", false);


        Room currentRoom = optionalRoom.get();

        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (!optionalHotel.isPresent())
            return new ApiResponse("Not found.", false);

        Hotel hotel = optionalHotel.get();

        String number = room.getNumber();
        Integer floor = room.getFloor();
        Integer size = room.getSize();
        if (roomRepository.existsByNumberAndFloorAndHotel(number, floor, hotel))
            return new ApiResponse("This name already exists in Room", false);

        currentRoom.setNumber(number);
        currentRoom.setFloor(floor);
        currentRoom.setSize(size);
        currentRoom.setHotel(hotel);
        Room saveRoom = roomRepository.save(currentRoom);
        return new ApiResponse("Successfully implemented", true, saveRoom);
    }

    public ApiResponse deleteRoomById(Integer id) {
        if (roomRepository.existsById(id))
            return new ApiResponse("Not found.", false);

        roomRepository.deleteById(id);
        return new ApiResponse("Successfully implemented", true);
    }

}
