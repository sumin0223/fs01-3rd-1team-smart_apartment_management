package com.jjld.domain.energy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "energy_measurement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnergyMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long measurementId;

    // ENERGY_DEVICE (1) ── (N) ENERGY_MEASUREMENT
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private EnergyDevice energyDevice;

    @Column(nullable = false)
    private Double voltage;

    @Column(nullable = false)
    private Double current;

    @Column(nullable = false)
    private Double power;

    @Column(nullable = false)
    private Double energyKwh; // 누적 소비 전력량

    @Column(nullable = false)
    private LocalDateTime createdAt;
}