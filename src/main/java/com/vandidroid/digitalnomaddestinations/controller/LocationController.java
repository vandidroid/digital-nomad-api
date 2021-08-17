package com.vandidroid.digitalnomaddestinations.controller;

import com.vandidroid.digitalnomaddestinations.model.dto.LocationCommand;
import com.vandidroid.digitalnomaddestinations.model.entity.DigitalNomad;
import com.vandidroid.digitalnomaddestinations.model.entity.Location;
import com.vandidroid.digitalnomaddestinations.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;

    @GetMapping("")
    public List<Location> findAll() {
        return locationService.findAll();
    }

    @GetMapping("/{id}")
    public Location findById(@PathVariable Long id) {
        return locationService.findById(id);
    }

    @GetMapping("/{id}/nomads")
    public Set<DigitalNomad> findNomadsById(@PathVariable Long id) {
        return locationService.findById(id).getDigitalNomads();
    }

    @PostMapping("")
    public Location add(@RequestBody LocationCommand locationCommand) {
        return locationService.add(locationCommand);
    }

    @PutMapping("/{id}")
    public Location update(@PathVariable Long id, @RequestBody LocationCommand locationCommand) {
        return locationService.update(id, locationCommand);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        locationService.deleteById(id);
    }
}
