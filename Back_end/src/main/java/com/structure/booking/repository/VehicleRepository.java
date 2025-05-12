package com.fpt.booking.repository;

import com.fpt.booking.domain.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByDriverId(Long driverId);
    Optional<Vehicle> findByPlateNumber(String plateNumber);
    List<Vehicle> findByVehicleTypeAndIsActive(String vehicleType, Boolean isActive);
} 