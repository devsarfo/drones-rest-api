package com.devsarfo.drone.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Table(name = "delivery_items")
@Entity
public class DeliveryItem
{
    public DeliveryItem()
    {

    }

    public DeliveryItem(Long deliveryId, String medication) {
        this.deliveryId = deliveryId;
        this.medication = medication;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long deliveryId;

    @NotEmpty
    @Column(nullable = false)
    private String medication;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }
}
