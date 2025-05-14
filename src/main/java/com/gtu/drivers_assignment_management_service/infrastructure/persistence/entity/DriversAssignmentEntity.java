package com.gtu.drivers_assignment_management_service.infrastructure.persistence.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drivers_assignment")
@Getter
@NoArgsConstructor
public class DriversAssignmentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int driverId;
    
    @Column(nullable = false)
    private String name;
    
    @ElementCollection
    @CollectionTable(
        name = "driver_routes", 
        joinColumns = @JoinColumn(name = "driver_id")
    )
    @Column(name = "route_id")
    private List<Long> routesAssigned = new ArrayList<>();


    public DriversAssignmentEntity(Long id, int driverId, String name,List<Long> routesAssigned) {
        this.id = id;
        this.driverId = driverId;
        this.name = name;
        this.routesAssigned = routesAssigned;
    }
}
