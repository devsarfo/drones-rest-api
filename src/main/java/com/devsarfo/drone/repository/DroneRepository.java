package com.devsarfo.drone.repository;

import com.devsarfo.drone.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Long>
{
    List<Drone> findAllByState(@Param("state") String state);

    @Query(value = "SELECT d FROM Drone d WHERE d.serialNumber = :serialNumber ")
    Drone findBySerialNumber(@Param("serialNumber") String serialNumber);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Drone d SET d.state = :state WHERE  d.serialNumber = :serialNumber")
    void setState(@Param("state") String status, @Param("serialNumber") String serialNumber);
}
