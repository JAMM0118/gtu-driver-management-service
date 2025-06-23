package com.gtu.drivers_assignment_management_service.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drivers_assignment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DriverAssignmentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private Long driverId;


    @Column(nullable = false)
    private Long routeId;

    @Column(nullable = true)
    private Long currentStopId;

    @Column(nullable = true)
    private Long latestStopId;

    @Column(nullable = true)
    private Long nextStopId;
}
