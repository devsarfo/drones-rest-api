package com.devsarfo.drone.repository;

import com.devsarfo.drone.model.Delivery;
import com.devsarfo.drone.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>
{

}