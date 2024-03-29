package com.vandidroid.digitalnomaddestinations.controller;

import com.vandidroid.digitalnomaddestinations.model.dto.DigitalNomadCommand;
import com.vandidroid.digitalnomaddestinations.model.entity.DigitalNomad;
import com.vandidroid.digitalnomaddestinations.service.DigitalNomadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
@CrossOrigin(origins = {"http://travel.dinodev.hu", "http://localhost:3000"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/nomads")
@Tag(name = "The Digital Nomad API", description = "The Digital Nomad API")
public class DigitalNomadController {
    private final DigitalNomadService digitalNomadService;

    @GetMapping(value = "", produces = {"application/json"})
    @Operation(summary = "List all nomads", description = "List all nomads")
    public List<DigitalNomad> findAll() {
        return digitalNomadService.findAll();
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    @Operation(summary = "Find a nomad", description = "Find a nomad by its id")
    public DigitalNomad findById(@Parameter(description = "The id of the nomad", required = true, example = "1") @Min(1) @PathVariable Long id) {
        return digitalNomadService.findById(id);
    }

    @PostMapping(value = "", produces = {"application/json"})
    @Operation(summary = "Insert a new nomad", description = "Insert a new nomad")
    @ResponseStatus(HttpStatus.CREATED)
    public DigitalNomad add(@Valid @RequestBody DigitalNomadCommand digitalNomadCommand) {
        return digitalNomadService.add(digitalNomadCommand);
    }

    @PutMapping(value = "/{id}", produces = {"application/json"})
    @Operation(summary = "Update a nomad", description = "Update a nomad by its id")
    public DigitalNomad update(@Parameter(description = "The id of the nomad", required = true, example = "1") @Min(1) @PathVariable Long id, @Valid @RequestBody DigitalNomadCommand digitalNomadCommand) {
        return digitalNomadService.update(id, digitalNomadCommand);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    @Operation(summary = "Delete a nomad", description = "Delete a nomad by its id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "The id of the nomad", required = true, example = "1") @Min(1) @PathVariable Long id) {
        digitalNomadService.deleteById(id);
    }

    @PutMapping(value = "/{id}/relocate/{locationId}", produces = {"application/json"})
    @Operation(summary = "Relocate a nomad", description = "Relocate a nomad by its id")
    public DigitalNomad relocate(@Parameter(description = "The id of the nomad", required = true, example = "1") @Min(1)  @PathVariable Long id, @Parameter(description = "The id of the location", required = true, example = "1") @Min(1)  @PathVariable Long locationId) {
        return digitalNomadService.relocate(id, locationId);
    }
}
