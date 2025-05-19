package com.structure.booking.domain.payload.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingRequest {
    private Long userId;
    private Long driverId;
    private Long vehicleId;
    private LocationRequest location;
    private String status;
    private Double price;
    private LocalDateTime pickupTime;
    private Boolean isActive;
} 