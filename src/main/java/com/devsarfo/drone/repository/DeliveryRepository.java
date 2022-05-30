package com.devsarfo.drone.repository;

import com.devsarfo.drone.model.Delivery;
import com.devsarfo.drone.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>
{

    @Query(value = "SELECT d FROM Delivery d WHERE d.drone = :drone AND d.deliveredAt IS NULL")
    Delivery findByDrone(@Param("drone") String drone);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Delivery d SET d.dispatchedAt = :dispatchedAt WHERE d.id = :Id")
    void setDispatched(@Param("dispatchedAt") Instant dispatchedAt, @Param("Id") Long Id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Delivery d SET d.deliveredAt = :deliveredAt WHERE  d.id = :Id")
    void setDelivered(@Param("deliveredAt") Instant deliveredAt, @Param("Id") Long Id);
}