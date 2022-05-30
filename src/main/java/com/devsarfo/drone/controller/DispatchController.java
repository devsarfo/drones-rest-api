package com.devsarfo.drone.controller;


import com.devsarfo.drone.data.request.DeliveryRequest;
import com.devsarfo.drone.data.response.ApiResponse;
import com.devsarfo.drone.data.response.DeliveryResponse;
import com.devsarfo.drone.model.Delivery;
import com.devsarfo.drone.model.Drone;
import com.devsarfo.drone.model.Medication;
import com.devsarfo.drone.service.DispatchService;
import com.devsarfo.drone.service.DroneService;
import com.devsarfo.drone.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api/dispatch/")
public class DispatchController
{
    @Autowired
    private DispatchService dispatchService;

    @Autowired
    private MedicationService medicationService;

    @Autowired
    private DroneService droneService;

    @PostMapping("load")
    public ResponseEntity<ApiResponse> load(@Valid @RequestBody DeliveryRequest param)
    {
        // Validate Drone Serial Number
        Drone drone = droneService.get(param.getSerialNumber());
        if(drone == null)
        {
            return  new ResponseEntity<ApiResponse>(new ApiResponse(
                    "error",
                    "Drone with serial number "+param.getSerialNumber()+" not found!",
                    null
            ), HttpStatus.NOT_FOUND);
        }
        else
        {
            //Check if IDLE
            if(!Objects.equals(drone.getState(), "IDLE"))
            {
                return  new ResponseEntity<ApiResponse>(new ApiResponse(
                        "error",
                        "Drone is not available! State: " + drone.getState(),
                        drone
                ), HttpStatus.BAD_REQUEST);
            }

            //Check if IDLE
            if(drone.getBatteryCapacity() < 25)
            {
                return  new ResponseEntity<ApiResponse>(new ApiResponse(
                        "error",
                        "Drone is not available! Battery Capacity: " + drone.getBatteryCapacity()+"%",
                        drone
                ), HttpStatus.BAD_REQUEST);
            }
        }


        // Validate Medication Codes and Total Weight
        double weight = 0;
        for(String code : param.getMedications())
        {
            Medication medication = medicationService.get(code);
            if(medication == null)
            {
                return  new ResponseEntity<ApiResponse>(new ApiResponse(
                        "error",
                        "Medication with code "+code+" not found!",
                        null
                ), HttpStatus.NOT_FOUND);
            }
            else
            {
                weight += medication.getWeight();
            }
        }

        //Check weight
        if(weight > drone.getWeightLimit())
        {
            return  new ResponseEntity<ApiResponse>(new ApiResponse(
                    "error",
                    "Total weight of items "+weight+" grams exceeds drone weight limit of " + drone.getWeightLimit()+ " grams",
                    drone
            ), HttpStatus.BAD_REQUEST);
        }


        DeliveryResponse deliveryResponse = dispatchService.load(param);

        ApiResponse response = new ApiResponse(
                "success",
                "Drone loaded successfully",
                deliveryResponse
        );

        return new ResponseEntity<ApiResponse>(response, HttpStatus.CREATED);
    }
}
