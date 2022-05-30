package com.devsarfo.drone.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name = "drones")
@Entity
public class Drone
{

    public Drone()
    {

    }

    public Drone(String serialNumber, String model, double weightLimit, double batteryCapacity, String state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }

    @Id
    @Size(max = 100, message = "100 characters max")
    @Column(nullable = false)
    private String serialNumber;

    @NotEmpty
    @Column(nullable = false)
    private String model;

    @NotNull
    @Max(value = 500, message = "500 gram max")
    @Column(nullable = false)
    private double weightLimit;

    @NotNull
    @Column(nullable = false)
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
