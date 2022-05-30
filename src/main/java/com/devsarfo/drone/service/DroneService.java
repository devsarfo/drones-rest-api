package com.devsarfo.drone.service;

import com.devsarfo.drone.model.Drone;
import com.devsarfo.drone.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DroneService
{
    @Autowired
    private DroneRepository droneRepository;

    public Drone register(Drone drone)
    {
        return droneRepository.save(drone);
    }

    public List<Drone> available(String state)
    {
        return droneRepository.findAllByState(state);
    }

    public Drone get(String serialNumber)
    {
        Drone drone = droneRepository.findBySerialNumber(serialNumber);
        return drone;
    }
}
