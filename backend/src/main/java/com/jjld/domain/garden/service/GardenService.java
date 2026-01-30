package com.jjld.domain.garden.service;

import com.jjld.domain.garden.dto.GardenReq;
import jakarta.validation.Valid;

public interface GardenService {
    void createGarden(GardenReq gardenReq);
}
