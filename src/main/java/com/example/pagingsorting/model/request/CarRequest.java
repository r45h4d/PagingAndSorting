package com.example.pagingsorting.model.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {
    private String brand;
    private String model;
    private String color;
    private Integer productionYear;
}
