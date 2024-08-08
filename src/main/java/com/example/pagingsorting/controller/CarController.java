package com.example.pagingsorting.controller;

import com.example.pagingsorting.model.criteria.CarCriteria;
import com.example.pagingsorting.model.criteria.PageCriteria;
import com.example.pagingsorting.model.request.CarRequest;
import com.example.pagingsorting.model.response.CarResponse;
import com.example.pagingsorting.model.response.PageableCarResponse;
import com.example.pagingsorting.service.abstraction.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;
@RestController
@RequestMapping("v1/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/{id}")
    public CarResponse getCar(@PathVariable Long id) {
        return carService.getCar(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void saveCar(@RequestBody CarRequest car) {
        carService.saveCar(car);
    }

    @PatchMapping("/{id}/color")
    @ResponseStatus(NO_CONTENT)
    public void setColor(@PathVariable Long id,
                         @RequestParam String color){
        carService.setColor(id, color);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
    }

    @GetMapping
    public PageableCarResponse getCars(PageCriteria pageCriteria,
                                       CarCriteria carCriteria){
        return carService.getCars(pageCriteria, carCriteria);
    }
}
