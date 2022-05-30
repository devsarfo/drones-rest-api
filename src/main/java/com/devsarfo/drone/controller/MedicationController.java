package com.devsarfo.drone.controller;

import com.devsarfo.drone.data.response.ApiResponse;
import com.devsarfo.drone.model.Drone;
import com.devsarfo.drone.model.Medication;
import com.devsarfo.drone.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/medication/")
public class MedicationController
{

    @Autowired
    private MedicationService medicationService;

    @PostMapping("register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody Medication param)
    {
        Medication medication = medicationService.register(param);
        ApiResponse response = new ApiResponse("success", "Medication created successfully", medication);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.CREATED);
    }

    @GetMapping("list")
    public ResponseEntity<ApiResponse> list()
    {
        List<Medication> medications =  medicationService.findAll();
        ApiResponse response = new ApiResponse("success", "Medications loaded successfully", medications);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }
}
