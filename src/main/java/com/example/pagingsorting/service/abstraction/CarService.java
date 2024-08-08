package com.example.pagingsorting.service.abstraction;

import com.example.pagingsorting.model.criteria.CarCriteria;
import com.example.pagingsorting.model.criteria.PageCriteria;
import com.example.pagingsorting.model.request.CarRequest;
import com.example.pagingsorting.model.response.CarResponse;
import com.example.pagingsorting.model.response.PageableCarResponse;
import org.springframework.stereotype.Service;

@Service
public interface CarService {
    CarResponse getCar(Long id);

    void saveCar(CarRequest car);

    void setColor(Long id, String color);

    PageableCarResponse getCars(PageCriteria pageCriteria, CarCriteria carCriteria);

    void deleteCar(Long id);
}
