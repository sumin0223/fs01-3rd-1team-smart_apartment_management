package com.jjld.domain.energy.entity;

import com.jjld.domain.energy.entity.Enum.AnalysisStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "energy_analysis")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnergyAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long analysisId;

    // ENERGY_DEVICE (1) ── (N) ENERGY_ANALYSIS
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private EnergyDevice energyDevice;

    // ENERGY_USAGE_SUMMARY (1) ── (N) ENERGY_ANALYSIS
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "summary_id", nullable = false)
    private EnergyUsageSummary usageSummary;

    // ENERGY_POLICY (1) ── (N) ENERGY_ANALYSIS
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id", nullable = false)
    private EnergyPolicy energyPolicy;

    @Column(nullable = false)
    private Double overusePercent;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AnalysisStatus analysisStatus;

    @Column(nullable = false)
    private Double estimatedWasteCost;

    @Column(nullable = false)
    private Double estimatedWasteKwh;

    @Column(length = 255)
    private String causeEstimate;

    @Column(nullable = false)
    private Integer breachCount24h;

    @Column(nullable = false)
    private Boolean singleBreachIgnored;

    @Column(nullable = false)
    private LocalDateTime analyzedAt;
}