package com.jjld.domain.energy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "energy_saving_result")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnergySavingResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long savingId;

    // ENERGY_DEVICE (1) ── (N) ENERGY_SAVING_RESULT
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private EnergyDevice energyDevice;

    // ENERGY_CONTROL_LOG (선택적 연결)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "control_id")
    private EnergyControlLog controlLog;

    @Column(nullable = false)
    private Double beforeKwh;

    @Column(nullable = false)
    private Double afterKwh;

    @Column(nullable = false)
    private Double savedKwh;

    @Column(nullable = false)
    private Double savedCost;

    @Column(nullable = false)
    private LocalDateTime evaluatedAt;
}
