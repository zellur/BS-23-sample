package com.zellur.brainstation23.controller;

import com.zellur.brainstation23.entity.Location;
import com.zellur.brainstation23.entity.User;
import com.zellur.brainstation23.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Callable;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LocationController {
    private final LocationService locationService;

    @RequestMapping(value = "/locationList", method = RequestMethod.GET)
    public Callable<ResponseEntity<List<Location>>> getLocationList() {
        log.debug("Received request for locations: ");
        return () -> ResponseEntity.ok(locationService.findAll());
    }
}
