package com.structure.booking.domain.payload.response;

import lombok.Data;

@Data
public class DriverResponse {
    private Long id;
    private String licenseNumber;
    private String vehicleType;
    private Boolean isAvailable;
    private Double rating;
    private Boolean isActive;
} 