package com.fpt.booking.domain.entities;

import com.fpt.booking.domain.enums.DateAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Driver driver;

    @Column(name = "plate_number", unique = true)
    private String plateNumber;

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "is_active")
    private Boolean isActive;
} 