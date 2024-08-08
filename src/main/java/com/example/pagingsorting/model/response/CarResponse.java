package com.example.pagingsorting.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarResponse {
    private Long id;
    private String brand;
    private String model;
    private String color;
    private Integer productionYear;
}
