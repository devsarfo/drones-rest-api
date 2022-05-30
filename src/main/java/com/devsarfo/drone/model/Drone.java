package com.devsarfo.drone.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.*;

@Table(name = "drones")
@Entity
public class Drone
{

    public Drone()
    {

    }

    public Drone(String serialNumber, String model, double weightLimit, double batteryCapacity, String state)
    {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }

    @Id
    @Size(max = 100, message = "Serial Number can be 100 characters maximum")
    @Column(nullable = false)
    private String serialNumber;

    @NotEmpty
    @Column(nullable = false)
    private String model;

    @Min(value = 0, message = "Weight limit can be 0 gram minimum")
    @Max(value = 500, message = "Weight limit can be 500 gram maximum")
    @Column(nullable = false, scale = 2)
    private double weightLimit;

    @Min(value = 0, message = "Battery level can be 0% minimum")
    @Max(value = 100, message = "Battery level can be 100% maximum")
    @Column(nullable = false, scale = 2)
    private double batteryCapacity;

    @NotEmpty
    @Column(nullable = false)
    private String state;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
