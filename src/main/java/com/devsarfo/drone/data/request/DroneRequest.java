package com.devsarfo.drone.data.request;

import javax.validation.constraints.NotEmpty;

public class DroneRequest
{
    public DroneRequest()
    {
    }

    @NotEmpty
    public DroneRequest(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    private String serialNumber;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

}
