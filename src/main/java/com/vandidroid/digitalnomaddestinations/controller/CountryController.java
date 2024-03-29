package com.vandidroid.digitalnomaddestinations.controller;

import com.vandidroid.digitalnomaddestinations.model.dto.CountryCommand;
import com.vandidroid.digitalnomaddestinations.model.entity.Country;
import com.vandidroid.digitalnomaddestinations.model.entity.DigitalNomad;
import com.vandidroid.digitalnomaddestinations.model.entity.Location;
import com.vandidroid.digitalnomaddestinations.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = {"http://travel.dinodev.hu", "http://localhost:3000"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/countries")
@Tag(name = "The Country API", description = "The Country API")
public class CountryController {
    private final CountryService countryService;

    @GetMapping(value = "", produces = {"application/json"})
    @Operation(summary = "List all countries", description = "List all countries")
    public List<Country> findAll() {
        return countryService.findAll();
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    @Operation(summary = "Find a country", description = "Find a country by its id")
    public Country findById(@Parameter(description = "The id of the country", required = true, example = "1") @Min(1) @PathVariable Long id) {
        return countryService.findById(id);
    }

    @GetMapping(value = "/{id}/locations", produces = {"application/json"})
    @Operation(summary = "Find all locations from a country", description = "Find all locations from a country by the country id")
    public Set<Location> findLocationsByCountryId(@Parameter(description = "The id of the country", required = true, example = "1") @Min(1) @PathVariable Long id) {
        return countryService.findById(id).getLocations();
    }

    @GetMapping(value = "/{id}/nomads", produces = {"application/json"})
    @Operation(summary = "Find all digital nomads from a country", description = "Find all digital nomads from a country by the country id")
    public Set<DigitalNomad> findDigitalNomadsByCountryId(@Parameter(description = "The id of the country", required = true, example = "1") @Min(1) @PathVariable Long id) {
        return countryService.findNomadsByCountryId(id);
    }

    @PostMapping(value = "", produces = {"application/json"})
    @Operation(summary = "Insert a new country", description = "Insert a new country")
    @ResponseStatus(HttpStatus.CREATED)
    public Country add(@Valid @RequestBody CountryCommand countryCommand) {
        return countryService.add(countryCommand);
    }

    @PutMapping(value = "/{id}", produces = {"application/json"})
    @Operation(summary = "Update a country", description = "Update a country by its id")
    public Country update(@Parameter(description = "The id of the country", required = true, example = "1") @Min(1) @PathVariable Long id, @Valid @RequestBody CountryCommand countryCommand) {
        return countryService.update(id, countryCommand);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    @Operation(summary = "Delete a country", description = "Delete a country by its id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "The id of the country", required = true, example = "1") @Min(1) @PathVariable Long id) {
        countryService.deleteById(id);
    }
}
