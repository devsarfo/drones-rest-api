package com.devsarfo.drone.repository;

import com.devsarfo.drone.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Long>
{

}
