package com.structure.booking.domain.enums;

public enum BookingStatus {
    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    DRIVER_ARRIVED("DRIVER_ARRIVED"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED");

    private String value;

    BookingStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
} 