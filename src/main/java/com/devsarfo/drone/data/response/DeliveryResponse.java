package com.devsarfo.drone.data.response;

import com.devsarfo.drone.model.Delivery;
import com.devsarfo.drone.model.DeliveryItem;

import java.time.Instant;
import java.util.List;

public class DeliveryResponse
{

    public DeliveryResponse()
    {
    }

    public DeliveryResponse(Delivery delivery, List<DeliveryItem> items) {
        this.delivery = delivery;
        this.items = items;
        this.timestamp = Instant.now();
    }

    private Delivery delivery;
    private List<DeliveryItem> items;
    private Instant timestamp;

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public List<DeliveryItem> getItems() {
        return items;
    }

    public void setItems(List<DeliveryItem> items) {
        this.items = items;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
