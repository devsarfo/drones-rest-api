package com.devsarfo.drone.service;

import com.devsarfo.drone.model.Drone;
import com.devsarfo.drone.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneService
{
    @Autowired
    private DroneRepository droneRepository;

    public Drone register(Drone drone)
    {
        return droneRepository.save(drone);
    }
}
