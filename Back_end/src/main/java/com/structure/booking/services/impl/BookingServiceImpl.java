package com.structure.booking.services.impl;

import com.structure.booking.domain.entities.Booking;
import com.structure.booking.repository.BookingRepository;
import com.structure.booking.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.structure.booking.domain.payload.request.BookingRequest;
import com.structure.booking.domain.payload.response.BookingResponse;
import com.structure.booking.domain.payload.request.LocationRequest;
import com.structure.booking.domain.payload.response.LocationResponse;
import com.structure.booking.domain.payload.response.UserResponse;
import com.structure.booking.domain.payload.response.DriverResponse;
import com.structure.booking.domain.payload.response.VehicleResponse;
import com.structure.booking.repository.UserRepository;
import com.structure.booking.repository.DriverRepository;
import com.structure.booking.repository.VehicleRepository;
import com.structure.booking.common.CommonMapper;
import java.util.Optional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final CommonMapper commonMapper;

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        Booking booking = new Booking();
        booking.setPrice(bookingRequest.getPrice());
        booking.setStatus(com.structure.booking.domain.enums.BookingStatus.valueOf(bookingRequest.getStatus()));
        booking.setPickupTime(bookingRequest.getPickupTime());
        booking.setIsActive(bookingRequest.getIsActive());
        // set user
        userRepository.findById(bookingRequest.getUserId()).ifPresent(booking::setUser);
        // set driver
        driverRepository.findById(bookingRequest.getDriverId()).ifPresent(booking::setDriver);
        // set vehicle
        vehicleRepository.findById(bookingRequest.getVehicleId()).ifPresent(booking::setVehicle);
        // set location
        if (bookingRequest.getLocation() != null) {
            LocationRequest locReq = bookingRequest.getLocation();
            com.structure.booking.domain.entities.Location location = new com.structure.booking.domain.entities.Location();
            location.setPickupLatitude(locReq.getPickupLatitude());
            location.setPickupLongitude(locReq.getPickupLongitude());
            location.setPickupAddress(locReq.getPickupAddress());
            location.setDropoffLatitude(locReq.getDropoffLatitude());
            location.setDropoffLongitude(locReq.getDropoffLongitude());
            location.setDropoffAddress(locReq.getDropoffAddress());
            booking.setLocation(location);
        }
        Booking saved = bookingRepository.save(booking);
        return commonMapper.convertToResponse(saved, BookingResponse.class);
    }

    @Override
    public BookingResponse getBookingById(Long id) {
        return bookingRepository.findById(id)
                .map(b -> commonMapper.convertToResponse(b, BookingResponse.class))
                .orElse(null);
    }

    @Override
    public List<BookingResponse> getBookingsByUser(Long userId) {
        return bookingRepository.findAll().stream()
                .filter(b -> b.getUser() != null && b.getUser().getId().equals(userId))
                .map(b -> commonMapper.convertToResponse(b, BookingResponse.class))
                .toList();
    }

    @Override
    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(b -> commonMapper.convertToResponse(b, BookingResponse.class))
                .toList();
    }

    @Override
    public BookingResponse acceptBooking(Long bookingId, Long driverId) {
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        Optional<com.structure.booking.domain.entities.Driver> driverOpt = driverRepository.findById(driverId);
        if (bookingOpt.isEmpty() || driverOpt.isEmpty()) {
            throw new IllegalArgumentException("Không tìm thấy booking hoặc tài xế");
        }
        Booking booking = bookingOpt.get();
        if (!com.structure.booking.domain.enums.BookingStatus.PENDING.name().equals(booking.getStatus().name())) {
            throw new IllegalStateException("Chỉ có thể nhận chuyến đi ở trạng thái PENDING");
        }
        booking.setDriver(driverOpt.get());
        booking.setStatus(com.structure.booking.domain.enums.BookingStatus.ACCEPTED);
        Booking saved = bookingRepository.save(booking);
        return commonMapper.convertToResponse(saved, BookingResponse.class);
    }

    @Override
    public BookingResponse completeBooking(Long bookingId) {
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        if (bookingOpt.isEmpty()) {
            throw new IllegalArgumentException("Không tìm thấy booking");
        }
        Booking booking = bookingOpt.get();
        if (!com.structure.booking.domain.enums.BookingStatus.IN_PROGRESS.name().equals(booking.getStatus().name()) &&
            !com.structure.booking.domain.enums.BookingStatus.ACCEPTED.name().equals(booking.getStatus().name())) {
            throw new IllegalStateException("Chỉ có thể hoàn thành chuyến đi ở trạng thái IN_PROGRESS hoặc ACCEPTED");
        }
        booking.setStatus(com.structure.booking.domain.enums.BookingStatus.COMPLETED);
        Booking saved = bookingRepository.save(booking);
        return commonMapper.convertToResponse(saved, BookingResponse.class);
    }
} 