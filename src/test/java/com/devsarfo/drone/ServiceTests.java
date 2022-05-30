package com.devsarfo.drone;

import com.devsarfo.drone.data.response.ApiResponse;
import com.devsarfo.drone.model.Drone;
import com.devsarfo.drone.repository.DroneRepository;
import com.devsarfo.drone.service.DroneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceTests
{

    @Mock
    DroneRepository droneRepository;

    @InjectMocks
    DroneService droneService;

    @Test
    public void availableDrones() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<Drone> list = new ArrayList<Drone>();
        Drone drone1 = new Drone("DRZ6KULBUA9S", "Lightweight", 500, 64, "IDLE");
        Drone drone2 = new Drone("DRLZ6KUBUA9S", "Cruiserweight", 640, 14, "IDLE");
        Drone drone3 = new Drone("DRBUAZ6KUL9S", "Middleweight", 840, 84, "IDLE");

        list.add(drone1);
        list.add(drone2);
        list.add(drone3);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus("success");
        apiResponse.setMessage("Available drone loaded");
        apiResponse.setData(list);

        when(droneRepository.findAllByState("IDLE")).thenReturn(list);

        List<Drone> available =  droneService.available("IDLE");
        assertEquals(3, available.size());
        verify(droneRepository, times(1)).findAllByState("IDLE");
    }
}
