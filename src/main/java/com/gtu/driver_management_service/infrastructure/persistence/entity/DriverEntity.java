package com.gtu.driver_management_service.infrastructure.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drivers")
@Getter
@NoArgsConstructor
public class DriverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(unique = true, nullable = false)
    private String email;
    
    @Column
    private String phoneNumber;
    
    @Column
    private Integer[] routeAssigned;
    
    public DriverEntity(String name, String email, String phoneNumber, Integer[] routeAssigned) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.routeAssigned = routeAssigned;
    }
}
