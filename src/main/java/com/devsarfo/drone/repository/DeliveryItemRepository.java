package com.devsarfo.drone.repository;

import com.devsarfo.drone.model.DeliveryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryItemRepository extends JpaRepository<DeliveryItem, Long>
{

}
