package com.structure.booking.domain.payload.response;

import lombok.Data;

@Data
public class VehicleResponse {
    private Long id;
    private String plateNumber;
    private String model;
    private String color;
    private String vehicleType;
    private Boolean isActive;
} 