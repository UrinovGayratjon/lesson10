package com.example.lesson10_task_1.uz.urinov.repository;

import com.example.lesson10_task_1.uz.urinov.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
