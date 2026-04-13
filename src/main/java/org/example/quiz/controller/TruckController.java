package org.example.quiz.controller;

import lombok.RequiredArgsConstructor;
import org.example.quiz.dto.TruckDTO;
import org.example.quiz.service.TruckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trucks")
@RequiredArgsConstructor
public class TruckController {

    private final TruckService truckService;

    // ─── GET (ADMIN + DRIVER) ─────────────────────────────────────────────────

    /** GET /api/trucks → lista todos los camiones */
    @GetMapping
    public ResponseEntity<List<TruckDTO>> getAllTrucks() {
        return ResponseEntity.ok(truckService.getAllTrucks());
    }

    /** GET /api/trucks/{id} → obtiene un camión por ID */
    @GetMapping("/{id}")
    public ResponseEntity<TruckDTO> getTruckById(@PathVariable Long id) {
        return ResponseEntity.ok(truckService.getTruckById(id));
    }

    /** GET /api/trucks/driver/{userDriver} → camiones asignados a un driver */
    @GetMapping("/driver/{userDriver}")
    public ResponseEntity<List<TruckDTO>> getTrucksByDriver(@PathVariable String userDriver) {
        return ResponseEntity.ok(truckService.getTrucksByDriver(userDriver));
    }

    // ─── POST / PUT / DELETE (ADMIN only – enforced in SecurityConfig) ────────

    /** POST /api/trucks → crea un nuevo camión (solo ADMIN) */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TruckDTO> createTruck(@RequestBody TruckDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(truckService.createTruck(dto));
    }

    /** PUT /api/trucks/{id} → actualiza un camión (solo ADMIN) */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TruckDTO> updateTruck(@PathVariable Long id, @RequestBody TruckDTO dto) {
        return ResponseEntity.ok(truckService.updateTruck(id, dto));
    }

    /** DELETE /api/trucks/{id} → elimina un camión (solo ADMIN) */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTruck(@PathVariable Long id) {
        truckService.deleteTruck(id);
        return ResponseEntity.noContent().build();
    }
}
