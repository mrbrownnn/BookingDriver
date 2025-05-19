package com.structure.booking.domain.payload.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingResponse {
    private Long id;
    private UserResponse user;
    private DriverResponse driver;
    private VehicleResponse vehicle;
    private LocationResponse location;
    private String status;
    private Double price;
    private LocalDateTime pickupTime;
    private Boolean isActive;
} 