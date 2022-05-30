package com.devsarfo.drone.service;

import com.devsarfo.drone.data.request.DeliveryRequest;
import com.devsarfo.drone.data.response.DeliveryResponse;
import com.devsarfo.drone.data.response.DroneItemResponse;
import com.devsarfo.drone.model.Delivery;
import com.devsarfo.drone.model.DeliveryItem;
import com.devsarfo.drone.model.Drone;
import com.devsarfo.drone.model.Medication;
import com.devsarfo.drone.repository.DeliveryItemRepository;
import com.devsarfo.drone.repository.DeliveryRepository;
import com.devsarfo.drone.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class DispatchService
{

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private DeliveryItemRepository deliveryItemRepository;

    @Autowired
    private DroneRepository droneRepository;

    public DeliveryResponse load(DeliveryRequest param)
    {
        //Update Drone
        droneRepository.setState("LOADING", param.getSerialNumber());

        List<DeliveryItem> items = new ArrayList<>();

        //Create Delivery
        Delivery delivery = deliveryRepository.save(new Delivery(param.getSerialNumber(), param.getSource(), param.getDestination()));

        //Save Items
        for(String code : param.getMedications())
        {
            DeliveryItem deliveryItem = deliveryItemRepository.save(new DeliveryItem(delivery.getId(), code));
            items.add(deliveryItem);
        }

        //Update Drone
        droneRepository.setState("LOADED", param.getSerialNumber());

        return new DeliveryResponse(delivery, items);
    }

    public DroneItemResponse items(String serialNumber)
    {
        List<Medication> medications = new ArrayList<>();

        Delivery delivery = deliveryRepository.findByDrone(serialNumber);
        if(delivery != null)
        {
            medications = deliveryItemRepository.findAllMedicationByDeliveryId(delivery.getId());
        }

        return new DroneItemResponse(delivery, medications);
    }

    public Delivery get(String serialNumber)
    {
        return deliveryRepository.findByDrone(serialNumber);
    }

    public DeliveryResponse deliver(Delivery delivery)
    {
        //Update Delivery
        deliveryRepository.setDispatched(Instant.now(), delivery.getId());

        //Get Data
        delivery = deliveryRepository.findByDrone(delivery.getDrone());
        List<DeliveryItem> items = deliveryItemRepository.findAllByDeliveryId(delivery.getId());

        return new DeliveryResponse(delivery, items);
    }
}
