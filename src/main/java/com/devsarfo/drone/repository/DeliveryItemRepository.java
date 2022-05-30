package com.devsarfo.drone.repository;

import com.devsarfo.drone.model.Delivery;
import com.devsarfo.drone.model.DeliveryItem;
import com.devsarfo.drone.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryItemRepository extends JpaRepository<DeliveryItem, Long>
{
    @Query(value = "SELECT d FROM DeliveryItem d WHERE d.deliveryId = :deliveryId")
    List<DeliveryItem> findAllByDeliveryId(@Param("deliveryId") Long deliveryId);

    @Query(value = "SELECT m FROM DeliveryItem d LEFT JOIN Medication m ON m.code = d.medication WHERE d.deliveryId = :deliveryId")
    List<Medication> findAllMedicationByDeliveryId(@Param("deliveryId") Long deliveryId);
}
