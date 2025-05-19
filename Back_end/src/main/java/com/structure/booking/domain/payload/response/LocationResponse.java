package com.structure.booking.domain.payload.response;

import lombok.Data;

@Data
public class LocationResponse {
    private Double pickupLatitude;
    private Double pickupLongitude;
    private String pickupAddress;
    private Double dropoffLatitude;
    private Double dropoffLongitude;
    private String dropoffAddress;
} 