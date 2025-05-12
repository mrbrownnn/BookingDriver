package com.fpt.booking.repository;

import com.fpt.booking.domain.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByUserId(Long userId);
    List<Driver> findByIsAvailableAndIsActive(Boolean isAvailable, Boolean isActive);
    Optional<Driver> findByLicenseNumber(String licenseNumber);
} 