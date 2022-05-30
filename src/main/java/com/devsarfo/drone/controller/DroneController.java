package com.devsarfo.drone.controller;

import com.devsarfo.drone.data.response.ApiResponse;
import com.devsarfo.drone.model.Drone;
import com.devsarfo.drone.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
        ApiResponse response = new ApiResponse("success", "Drone registered successfully", drone);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.BAD_REQUEST);
    }
}
