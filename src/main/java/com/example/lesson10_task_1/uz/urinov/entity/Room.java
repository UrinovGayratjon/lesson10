package com.example.lesson10_task_1.uz.urinov.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private Integer floor;

    @Column(nullable = false)
    private Integer size;

    @ManyToOne(optional = false)
    private Hotel hotel;

    @CreatedDate
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;

}
