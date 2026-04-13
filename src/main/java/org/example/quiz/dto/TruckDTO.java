package org.example.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TruckDTO {
    private Long id;
    private String brand;
    private Double capacity;
    private String color;
    private String plate;
    private String userDriver;
}
