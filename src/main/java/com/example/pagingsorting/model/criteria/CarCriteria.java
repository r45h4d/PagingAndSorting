package com.example.pagingsorting.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarCriteria {
    private Integer yearFrom;
    private Integer yearTo;
    private String brand;
    private String model;
    private String color;
}
