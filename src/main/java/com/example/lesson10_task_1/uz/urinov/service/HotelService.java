package com.example.lesson10_task_1.uz.urinov.service;

import com.example.lesson10_task_1.uz.urinov.entity.Hotel;
import com.example.lesson10_task_1.uz.urinov.payload.ApiResponse;
import com.example.lesson10_task_1.uz.urinov.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;

    public ApiResponse getAllHotels() {
        return new ApiResponse("Successfully implemented", true, hotelRepository.findAll());
    }

    public ApiResponse getHotelById(Integer id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        return optionalHotel.map(hotel -> new ApiResponse("Successfully implemented", true, hotel)).orElseGet(() -> new ApiResponse("Not found", false));
    }


    public ApiResponse addHotel(String name) {

        if (hotelRepository.existsByName(name))
            return new ApiResponse("This name already exists in Hotel", false);

        Hotel newHotel = new Hotel();
        newHotel.setName(name);
        Hotel saveHotel = hotelRepository.save(newHotel);
        return new ApiResponse("Successfully implemented", true, saveHotel);
    }


    public ApiResponse editHotel(Integer id, String name) {

        Optional<Hotel> optionalHotel = hotelRepository.findById(id);

        if (!optionalHotel.isPresent())
            return new ApiResponse("Not found.", false);


        Hotel hotel = optionalHotel.get();

        if (hotelRepository.existsByNameAndIdNot(name, id))
            return new ApiResponse("This name already exists", false);

        hotel.setName(name);
        Hotel saveHotel = hotelRepository.save(hotel);
        return new ApiResponse("Successfully implemented", true, saveHotel);
    }

    public ApiResponse deleteHotelById(Integer id) {
        if (hotelRepository.existsById(id))
            return new ApiResponse("Not found.", false);

        hotelRepository.deleteById(id);
        return new ApiResponse("Successfully implemented", true);
    }

}
