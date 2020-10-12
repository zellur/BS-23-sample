package com.zellur.brainstation23.service;

import com.zellur.brainstation23.entity.Location;
import com.zellur.brainstation23.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}
