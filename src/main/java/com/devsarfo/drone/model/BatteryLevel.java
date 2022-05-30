package com.devsarfo.drone.model;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "battery_levels")
@Entity
public class BatteryLevel
{
    public BatteryLevel()
    {
    }

    public BatteryLevel(String serialNumber, String state, double batteryLevel) {
        this.serialNumber = serialNumber;
        this.state = state;
        this.batteryLevel = batteryLevel;
        this.timestamp = Instant.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private double batteryLevel;

    @Column(nullable = false)
    private Instant timestamp;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
