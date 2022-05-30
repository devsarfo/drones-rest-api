package com.devsarfo.drone;

import com.devsarfo.drone.model.Delivery;
import com.devsarfo.drone.model.Drone;
import com.devsarfo.drone.model.Medication;
import com.devsarfo.drone.repository.DeliveryRepository;
import com.devsarfo.drone.repository.DroneRepository;
import com.devsarfo.drone.repository.MedicationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTests
{

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    MedicationRepository medicationRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Test
    public void drone() {
        Drone drone = new Drone("DRZ6KULBUA9S", "Lightweight", 500, 64, "IDLE");
        droneRepository.save(drone);

        Iterable<Drone> drones = droneRepository.findAll();
        Assertions.assertThat(drones).extracting(Drone::getSerialNumber).contains(drone.getSerialNumber());
        droneRepository.deleteAll();
        Assertions.assertThat(droneRepository.findAll()).isEmpty();
    }

    @Test
    public void medication() {
        Medication medication = new Medication("Paracetamol", 250, "LBUA9S", "paracetamol.png");
        medicationRepository. save(medication);

        Iterable<Medication> medications = medicationRepository.findAll();
        Assertions.assertThat(medications).extracting(Medication::getCode).contains(medication.getCode());
        medicationRepository.deleteAll();
        Assertions.assertThat(medicationRepository.findAll()).isEmpty();
    }

    @Test
    public void delivery() {
        Delivery delivery = new Delivery("DRZ6KULBUA9S", "Plovdiv", "Sofia");
        deliveryRepository. save(delivery);

        Iterable<Delivery> deliveries = deliveryRepository.findAll();
        Assertions.assertThat(deliveries).extracting(Delivery::getDrone).contains(delivery.getDrone());
        deliveryRepository.deleteAll();
        Assertions.assertThat(deliveryRepository.findAll()).isEmpty();
    }

}
