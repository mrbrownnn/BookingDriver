package com.structure.booking.repository;

import com.structure.booking.domain.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Thêm các phương thức custom nếu phát triển trong dự án tương lai
} 