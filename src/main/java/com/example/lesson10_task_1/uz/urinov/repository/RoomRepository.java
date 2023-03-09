package com.example.lesson10_task_1.uz.urinov.repository;

import com.example.lesson10_task_1.uz.urinov.entity.Hotel;
import com.example.lesson10_task_1.uz.urinov.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    boolean existsByNumberAndFloorAndHotel(String number, Integer floor, Hotel hotel);
    Page<Room> findAllByHotel_Id(Integer hotel_Id, Pageable pageable);

}
