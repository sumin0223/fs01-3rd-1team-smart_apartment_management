package com.jjld.domain.energy.entity;

import com.jjld.domain.energy.entity.Enum.DeviceStatus;
import com.jjld.domain.energy.entity.Enum.DeviceType;
import com.jjld.domain.house.entity.House;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "energy_device")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnergyDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;

    @Column(nullable = false, unique = true, length = 30)
    private String deviceCode; // DEV-1001

    @Column(nullable = false, length = 100)
    private String deviceName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private DeviceType deviceType;

    @Column(nullable = false, length = 100)
    private String location;

    // HOUSE (1) ── (N) ENERGY_DEVICE (nullable)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

    @Column(nullable = false)
    private LocalDateTime installedAt;

    private LocalDate lastCheckDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private DeviceStatus deviceStatus;

    @Column(nullable = false)
    private Boolean isOperating;
}