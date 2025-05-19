package com.structure.booking.services;

import com.structure.booking.domain.entities.Booking;
import java.util.List;
import com.structure.booking.domain.payload.request.BookingRequest;
import com.structure.booking.domain.payload.response.BookingResponse;

public interface BookingService {
    BookingResponse createBooking(BookingRequest bookingRequest);
    BookingResponse getBookingById(Long id);
    List<BookingResponse> getBookingsByUser(Long userId);
    List<BookingResponse> getAllBookings();
    BookingResponse acceptBooking(Long bookingId, Long driverId);
    BookingResponse completeBooking(Long bookingId);
} 