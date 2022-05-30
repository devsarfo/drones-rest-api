package com.devsarfo.drone.controller;

import com.devsarfo.drone.data.response.ApiResponse;
import com.devsarfo.drone.data.response.BatteryResponse;
import com.devsarfo.drone.model.Drone;
import com.devsarfo.drone.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/drone/")
public class DroneController
{
    @Autowired
    private DroneService droneService;

    @PostMapping("register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody Drone param)
    {
        Drone drone = droneService.register(param);
        ApiResponse response = new ApiResponse(
                "success",
                "Drone registered successfully",
                drone
        );

        return new ResponseEntity<ApiResponse>(response, HttpStatus.CREATED);
    }

    @GetMapping("available")
    public ResponseEntity<ApiResponse> available()
    {
        List<Drone> drones = droneService.available("IDLE");
        ApiResponse response = new ApiResponse(
                "success",
                "Available drones loaded successfully",
                drones
        );

        return new ResponseEntity<ApiResponse>(response, HttpStatus.CREATED);
    }

    @GetMapping("battery")
    public ResponseEntity<ApiResponse> battery(@RequestParam(name = "serialNumber") String serialNumber)
    {
        if(serialNumber.trim().isEmpty())
        {
            return new ResponseEntity<ApiResponse>(new ApiResponse(
                    "error",
                    "Serial Number is required",
                    null
            ), HttpStatus.BAD_REQUEST);
        }
        else
        {
            Drone drone = droneService.get(serialNumber);
            if(drone == null)
            {
                return  new ResponseEntity<ApiResponse>(new ApiResponse(
                        "error",
                        "Drone with serial number "+serialNumber+" not found!",
                        null
                ), HttpStatus.NOT_FOUND);
            }
            else
            {
                return  new ResponseEntity<ApiResponse>(new ApiResponse(
                        "success",
                        "Drone loaded successfully!",
                        new BatteryResponse(drone.getSerialNumber(), drone.getBatteryCapacity()+"%")
                ), HttpStatus.OK);
            }
        }
    }
}
