package com.structure.booking.repository;

import com.structure.booking.domain.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // thêm feature nếu phát triển thêm
}