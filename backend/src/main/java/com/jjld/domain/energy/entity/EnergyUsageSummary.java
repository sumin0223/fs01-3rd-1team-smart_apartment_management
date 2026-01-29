package com.jjld.domain.energy.entity;

import com.jjld.domain.energy.entity.Enum.PeriodType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "energy_usage_summary")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnergyUsageSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long summaryId;

    // ENERGY_DEVICE (1) ── (N) ENERGY_USAGE_SUMMARY
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private EnergyDevice energyDevice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PeriodType periodType;

    @Column(nullable = false)
    private LocalDate periodDate;

    private Integer timeSlot; // 0~23, TIME_SLOT일 때만 사용

    @Column(nullable = false)
    private Double actualKwh;

    @Column(nullable = false)
    private Double expectedKwh;

    @Column(nullable = false)
    private Double wasteKwh;

    @CreationTimestamp
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime createdAt;
}