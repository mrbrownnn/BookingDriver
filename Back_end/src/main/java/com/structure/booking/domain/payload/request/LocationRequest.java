package com.structure.booking.domain.payload.request;

import lombok.Data;

@Data
public class LocationRequest {
    private Double pickupLatitude;
    private Double pickupLongitude;
    private String pickupAddress;
    private Double dropoffLatitude;
    private Double dropoffLongitude;
    private String dropoffAddress;
} 