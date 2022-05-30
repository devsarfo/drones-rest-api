package com.devsarfo.drone.repository;

import com.devsarfo.drone.model.BatteryLevel;
import com.devsarfo.drone.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatteryLevelRepository  extends JpaRepository<BatteryLevel, Long>
{

}
