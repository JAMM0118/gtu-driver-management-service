package com.gtu.drivers_assignment_management_service.infrastructure.client;

import com.gtu.drivers_assignment_management_service.domain.model.Route;
import com.gtu.drivers_assignment_management_service.domain.model.Stop;
import com.gtu.drivers_assignment_management_service.infrastructure.client.dto.ClientResponse;
import com.gtu.drivers_assignment_management_service.infrastructure.client.dto.RouteResponse;
import com.gtu.drivers_assignment_management_service.infrastructure.client.dto.StopResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;





class RouteClientAdapterTest {

    private RouteClient routeClient;
    private RouteClientAdapter routeClientAdapter;

    @BeforeEach
    void setUp() {
        routeClient = mock(RouteClient.class);
        routeClientAdapter = new RouteClientAdapter(routeClient);
    }

    @Test
    void getRouteById_shouldReturnRoute_whenRouteExists() {
        Long routeId = 1L;
        RouteResponse route = new RouteResponse(routeId, "Route 1", List.of(10L, 20L));
       
        when(routeClient.getRouteById(routeId)).thenReturn(new ClientResponse<>("Success", route, 200));

        var result = routeClientAdapter.getRouteById(routeId);

        assertNotNull(result);
        assertEquals(routeId, result.getId());
        assertEquals("Route 1", result.getName());
        assertEquals(List.of(10L, 20L), result.getStops());
    }

    @Test
    void getRouteById_shouldReturnNull_whenRouteDoesNotExist() {
        when(routeClient.getRouteById(1L)).thenReturn(null);

        Route result = routeClientAdapter.getRouteById(1L);

        assertNull(result);
    }

    @Test
    void getStopsByRouteId_shouldReturnListOfStops_whenRouteAndStopsExist() {
        Long routeId = 1L;
        List<Long> stopIds = List.of(100L, 200L);
        RouteResponse route = new RouteResponse(routeId, "Route 1", stopIds);

        when(routeClient.getRouteById(routeId)).thenReturn(new ClientResponse<>("Success", route, 200));

        StopResponse stop1 = new StopResponse(100L, "Stop 1", 10.0, 20.0);
        StopResponse stop2 = new StopResponse(200L, "Stop 2", 30.0, 40.0);

        when(routeClient.getStopById(100L)).thenReturn(new ClientResponse<>("Success", stop1, 200));
        when(routeClient.getStopById(200L)).thenReturn(new ClientResponse<>("Success", stop2, 200));

        List<Stop> stops = routeClientAdapter.getStopsByRouteId(routeId);

        assertEquals(2, stops.size());
        assertEquals(stop1.id(), stops.get(0).getId());
        assertEquals(stop2.id(), stops.get(1).getId());
    }

    @Test
    void getStopsByRouteId_shouldReturnEmptyList_whenRouteDoesNotExist() {
        when(routeClient.getRouteById(1L)).thenReturn(null);

        List<Stop> stops = routeClientAdapter.getStopsByRouteId(1L);

        assertNotNull(stops);
        assertTrue(stops.isEmpty());
    }

    @Test
    void getStopsByRouteId_shouldReturnListWithNulls_whenSomeStopsDoNotExist() {
        Long routeId = 1L;
        List<Long> stopIds = List.of(100L, 200L);
        RouteResponse route = new RouteResponse(routeId, "Route 1", stopIds);
        when(routeClient.getRouteById(routeId)).thenReturn(new ClientResponse<>("Success", route, 200));

        StopResponse stop1 = new StopResponse(100L, "Stop 1", 10.0, 20.0);

        when(routeClient.getStopById(100L)).thenReturn(new ClientResponse<>("Success", stop1, 200));
        when(routeClient.getStopById(200L)).thenReturn(null);

        List<Stop> stops = routeClientAdapter.getStopsByRouteId(routeId);

        assertEquals(2, stops.size());
        assertEquals(stop1.id(), stops.get(0).getId());
        assertNull(stops.get(1));
    }

    @Test
    void getStopById_shouldReturnStop_whenStopExists() {
        Long stopId = 100L;
        StopResponse stopResponse = new StopResponse(stopId, "Stop 1", 10.0, 20.0);
        when(routeClient.getStopById(stopId)).thenReturn(new ClientResponse<>("Success", stopResponse, 200));

        Stop result = routeClientAdapter.getStopById(stopId);

        assertNotNull(result);
        assertEquals(stopId, result.getId());
        assertEquals("Stop 1", result.getName());
        assertEquals(10.0, result.getLatitude());
        assertEquals(20.0, result.getLongitude());
    }

    @Test
    void getStopById_shouldReturnNull_whenStopDoesNotExist() {
        when(routeClient.getStopById(100L)).thenReturn(null);

        Stop result = routeClientAdapter.getStopById(100L);

        assertNull(result);
    }
}