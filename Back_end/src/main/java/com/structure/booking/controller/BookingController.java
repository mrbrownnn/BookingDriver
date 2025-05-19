package com.structure.booking.controller;

import com.structure.booking.domain.entities.Booking;
import com.structure.booking.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.structure.booking.domain.payload.request.BookingRequest;
import com.structure.booking.domain.payload.response.BookingResponse;
import org.springframework.validation.annotation.Validated;
import com.structure.booking.controller.base.BaseController;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController extends BaseController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> createBooking(@Validated @RequestBody BookingRequest bookingRequest) {
        BookingResponse response = bookingService.createBooking(bookingRequest);
        return createSuccessResponse("Tạo booking thành công", response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        BookingResponse booking = bookingService.getBookingById(id);
        if (booking == null) {
            return createFailureResponse("404", "Không tìm thấy booking", null);
        }
        return createSuccessResponse("Lấy booking thành công", booking);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getBookingsByUser(@PathVariable Long userId) {
        return createSuccessResponse("Lấy danh sách booking theo user thành công", bookingService.getBookingsByUser(userId));
    }

    @GetMapping
    public ResponseEntity<?> getAllBookings() {
        return createSuccessResponse("Lấy tất cả booking thành công", bookingService.getAllBookings());
    }

    @PutMapping("/accept")
    public ResponseEntity<?> acceptBooking(@RequestParam Long bookingId, @RequestParam Long driverId) {
        BookingResponse response = bookingService.acceptBooking(bookingId, driverId);
        return createSuccessResponse("Tài xế đã nhận chuyến đi", response);
    }

    @PutMapping("/complete")
    public ResponseEntity<?> completeBooking(@RequestParam Long bookingId) {
        BookingResponse response = bookingService.completeBooking(bookingId);
        return createSuccessResponse("Tài xế đã hoàn thành chuyến đi", response);
    }
} 