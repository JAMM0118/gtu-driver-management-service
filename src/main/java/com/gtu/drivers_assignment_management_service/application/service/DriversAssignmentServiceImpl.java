package com.gtu.drivers_assignment_management_service.application.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.gtu.drivers_assignment_management_service.domain.model.DriverAssignment;
import com.gtu.drivers_assignment_management_service.domain.repository.DriverAssignmentRepository;
import com.gtu.drivers_assignment_management_service.domain.service.DriversAssignmentService;
import com.gtu.drivers_assignment_management_service.domain.service.RouteService;
import com.gtu.drivers_assignment_management_service.infrastructure.logs.LogPublisher;

@Service
public class DriversAssignmentServiceImpl implements DriversAssignmentService {
    private final DriverAssignmentRepository driversAssignmentRepository;
    private final RouteService routeService;
    private final Map<Long, Boolean> completeDriverRouteMap = new ConcurrentHashMap<>();

    private static final double MAX_DISTANCE_METERS = 20.0;

    private final LogPublisher logPublisher;

    public DriversAssignmentServiceImpl(DriverAssignmentRepository driversAssignmentRepository,
            RouteService routeService, LogPublisher logPublisher) {
        this.driversAssignmentRepository = driversAssignmentRepository;
        this.routeService = routeService;
        this.logPublisher = logPublisher;
    }

    @Override
    public DriverAssignment assignmentDriver(Long driverId, Long routeId) {
        logPublisher.sendLog(
                Instant.now().toString(),
                "driver-management-service",
                "INFO",
                "Assigning driver to route",
                Map.of("driverId", driverId));
        DriverAssignment driverAssignment = new DriverAssignment();
        driverAssignment.setDriverId(driverId);
        driverAssignment.setRouteId(routeId);
        driverAssignment.setCurrentStopId(null);
        driverAssignment.setLatestStopId(null);
        var route = routeService.getRouteById(routeId);
        if (route == null || route.getStops() == null || route.getStops().isEmpty()) {
            throw new IllegalArgumentException("Route not found or has no stops");
        }
        driverAssignment.setNextStopId(route.getStops().get(0));
        return driversAssignmentRepository.save(driverAssignment);
    }

    @Override
    public void updateCurrentStopId(Long driverId, double latitude, double longitude) {
        List<DriverAssignment> driverAssignments = driversAssignmentRepository.findAll().stream()
                .filter(assignment -> assignment.getDriverId().equals(driverId))
                .toList();

        for (DriverAssignment driverAssignment : driverAssignments) {
            if (tryUpdateAssignmentStop(driverAssignment, latitude, longitude)) {
                return;
            }
        }
    }

    private boolean tryUpdateAssignmentStop(DriverAssignment driverAssignment, double latitude, double longitude) {
        var route = routeService.getRouteById(driverAssignment.getRouteId());
        if (route == null || route.getStops() == null || route.getStops().isEmpty()) {
            return false;
        }

        var currentStop = driverAssignment.getCurrentStopId();
        List<Long> currentRoutes = new ArrayList<>(route.getStops());

        if (Boolean.TRUE.equals(completeDriverRouteMap.get(driverAssignment.getDriverId()))) {
            Collections.reverse(currentRoutes);
        }

        for (int i = 0; i < currentRoutes.size(); i++) {
            var stop = currentRoutes.get(i);
            var stopLocation = routeService.getStopById(stop);
            if (stopLocation == null)
                continue;

            double distance = calculateDistance(latitude, longitude, stopLocation.getLatitude(),
                    stopLocation.getLongitude());

            if (distance <= MAX_DISTANCE_METERS && (currentStop == null || !currentStop.equals(stop))) {
                updateAssignmentAtStop(driverAssignment, currentStop, stop, currentRoutes, i);
                return true;
            }

            if (currentStop != null && currentStop.equals(stop) && distance > MAX_DISTANCE_METERS) {
                updateAssignmentAwayFromStop(driverAssignment, currentStop, currentRoutes, i);
                return true;
            }
        }
        return false;
    }

    private void updateAssignmentAtStop(DriverAssignment driverAssignment, Long currentStop, Long stop,
            List<Long> stops, int i) {
        driverAssignment.setLatestStopId(currentStop);
        driverAssignment.setCurrentStopId(stop);
        var hasCompletedRoute = completeDriverRouteMap.getOrDefault(driverAssignment.getDriverId(), false);
        if (stops.size() - 1 == i) {

            completeDriverRouteMap.put(driverAssignment.getDriverId(), !hasCompletedRoute);
            driverAssignment.setNextStopId(null);
        } else {
            completeDriverRouteMap.put(driverAssignment.getDriverId(), hasCompletedRoute);
            driverAssignment.setNextStopId(stops.get(i + 1));
        }
        driversAssignmentRepository.update(driverAssignment);
    }

    private void updateAssignmentAwayFromStop(DriverAssignment driverAssignment, Long currentStop,
            List<Long> stops, int i) {
        driverAssignment.setLatestStopId(currentStop);
        driverAssignment.setCurrentStopId(null);
        var hasCompletedRoute = completeDriverRouteMap.getOrDefault(driverAssignment.getDriverId(), false);
        if (i == stops.size() - 1) {
            completeDriverRouteMap.put(driverAssignment.getDriverId(), !hasCompletedRoute);
            driverAssignment.setNextStopId(null);
        } else {
            completeDriverRouteMap.put(driverAssignment.getDriverId(), hasCompletedRoute);
            driverAssignment.setNextStopId(stops.get(i + 1));
        }
        driversAssignmentRepository.update(driverAssignment);
    }

    @Override
    public List<DriverAssignment> getAllAssignedDriversByRouteId(Long routeId) {
        return driversAssignmentRepository.findAll().stream()
                .filter(assignment -> assignment.getRouteId().equals(routeId))
                .toList();
    }

    @Override
    public DriverAssignment getDriverAssignmentById(Long id) {
        return driversAssignmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Driver assignment not found"));
    }

    @Override
    public DriverAssignment deleteDriverAssignmentById(Long id) {
        DriverAssignment existingAssignment = driversAssignmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Driver assignment not found"));

        driversAssignmentRepository.deleteById(id);
        return existingAssignment;
    }

    @Override
    public List<DriverAssignment> getAllAssignedDrivers() {
        return driversAssignmentRepository.findAll();
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    @Override
    public DriverAssignment getDriverAssignmentByDriverId(Long driverId) {
        return driversAssignmentRepository.getDriverAssignmentByDriverId(driverId)
                .orElseThrow(
                        () -> new IllegalArgumentException("Driver assignment not found for driver ID: " + driverId));
    }
}
