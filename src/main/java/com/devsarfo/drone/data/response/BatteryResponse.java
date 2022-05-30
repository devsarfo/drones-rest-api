package com.devsarfo.drone.data.response;


import java.sql.Timestamp;
import java.time.Instant;

public class BatteryResponse
{

    public BatteryResponse()
    {

    }

    public BatteryResponse(String serialNumber, String battery) {
        this.serialNumber = serialNumber;
        this.battery = battery;
        this.timestamp = Instant.now();

    }

    private String serialNumber;
    private String battery;
    private Instant timestamp;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
