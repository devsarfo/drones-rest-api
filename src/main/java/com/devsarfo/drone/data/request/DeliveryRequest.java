package com.devsarfo.drone.data.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class DeliveryRequest
{

    public DeliveryRequest()
    {

    }

    public DeliveryRequest(String serialNumber, String source, String destination, List<String> medications) {
        this.serialNumber = serialNumber;
        this.source = source;
        this.destination = destination;
        this.medications = medications;
    }

    @NotEmpty
    private String serialNumber;

    @NotEmpty
    private String source;

    @NotEmpty
    private String destination;

    @NotEmpty(message = "Requires list of medication codes to be delivered")
    private List<String> medications;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }
}
