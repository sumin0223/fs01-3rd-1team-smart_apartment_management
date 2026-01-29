package com.jjld.domain.cargate.entity;

import com.jjld.domain.house.entity.House;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registered_car")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisteredCar {

    @Id
    private Long id;  // vehicle.vehicle_id와 동일

    @MapsId //이걸 통해 pk공유
    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle; // 차량 엔티티

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id", nullable = false)
    private House house;
}
