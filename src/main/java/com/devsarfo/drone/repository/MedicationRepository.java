package com.devsarfo.drone.repository;

import com.devsarfo.drone.model.Drone;
import com.devsarfo.drone.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedicationRepository extends JpaRepository<Medication, Long>
{


    @Query(value = "SELECT m from Medication m where m.code = :code ")
    Medication findByCode(@Param("code") String code);
}
