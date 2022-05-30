package com.devsarfo.drone.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Table(name = "deliveries")
@Entity
public class Delivery
{
    public Delivery()
    {

    }

    public Delivery(String drone, String source, String destination)
    {
        this.drone = drone;
        this.source = source;
        this.destination = destination;
        this.createdAt = Instant.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String drone;

    @NotEmpty
    @Column(nullable = false)
    private String source;

    @NotEmpty
    @Column(nullable = false)
    private String destination;

    @CreatedDate
    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = true)
    private Instant dispatchAt;

    @Column(nullable = true)
    private Instant deliveredAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrone() {
        return drone;
    }

    public void setDrone(String drone) {
        this.drone = drone;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getDispatchAt() {
        return dispatchAt;
    }

    public void setDispatchAt(Instant dispatchAt) {
        this.dispatchAt = dispatchAt;
    }

    public Instant getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Instant deliveredAt) {
        this.deliveredAt = deliveredAt;
    }
}
