package com.jjld.domain.energy.entity;

import com.jjld.domain.admin.entity.Admin;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "energy_control_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnergyControlLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long controlId;

    // ENERGY_DEVICE (1) ── (N) ENERGY_CONTROL_LOG
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private EnergyDevice energyDevice;

    // ADMIN (1) ── (N) ENERGY_CONTROL_LOG
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @Column(nullable = false)
    private Boolean beforeState;

    @Column(nullable = false)
    private Boolean afterState;

    @Column(length = 255)
    private String reason;

    @Column(nullable = false)
    private LocalDateTime controlledAt;
}
