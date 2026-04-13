package org.example.quiz.service;

import lombok.RequiredArgsConstructor;
import org.example.quiz.dto.TruckDTO;
import org.example.quiz.entity.Truck;
import org.example.quiz.repository.TruckRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TruckService {

    private final TruckRepository truckRepository;

    // ─── READ (allowed for ADMIN and DRIVER) ─────────────────────────────────

    public List<TruckDTO> getAllTrucks() {
        return truckRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public TruckDTO getTruckById(Long id) {
        Truck truck = truckRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Truck not found with id: " + id));
        return toDTO(truck);
    }

    public List<TruckDTO> getTrucksByDriver(String userDriver) {
        return truckRepository.findByUserDriver(userDriver)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ─── CREATE (ADMIN only) ──────────────────────────────────────────────────

    public TruckDTO createTruck(TruckDTO dto) {
        if (truckRepository.findByPlate(dto.getPlate()).isPresent()) {
            throw new RuntimeException("Truck with plate already exists: " + dto.getPlate());
        }
        Truck truck = toEntity(dto);
        return toDTO(truckRepository.save(truck));
    }

    // ─── UPDATE (ADMIN only) ──────────────────────────────────────────────────

    public TruckDTO updateTruck(Long id, TruckDTO dto) {
        Truck existing = truckRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Truck not found with id: " + id));

        existing.setBrand(dto.getBrand());
        existing.setCapacity(dto.getCapacity());
        existing.setColor(dto.getColor());
        existing.setPlate(dto.getPlate());
        existing.setUserDriver(dto.getUserDriver());

        return toDTO(truckRepository.save(existing));
    }

    // ─── DELETE (ADMIN only) ──────────────────────────────────────────────────

    public void deleteTruck(Long id) {
        if (!truckRepository.existsById(id)) {
            throw new RuntimeException("Truck not found with id: " + id);
        }
        truckRepository.deleteById(id);
    }

    // ─── Mappers ──────────────────────────────────────────────────────────────

    private TruckDTO toDTO(Truck truck) {
        return TruckDTO.builder()
                .id(truck.getId())
                .brand(truck.getBrand())
                .capacity(truck.getCapacity())
                .color(truck.getColor())
                .plate(truck.getPlate())
                .userDriver(truck.getUserDriver())
                .build();
    }

    private Truck toEntity(TruckDTO dto) {
        return Truck.builder()
                .brand(dto.getBrand())
                .capacity(dto.getCapacity())
                .color(dto.getColor())
                .plate(dto.getPlate())
                .userDriver(dto.getUserDriver())
                .build();
    }
}
