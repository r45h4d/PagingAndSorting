package com.example.pagingsorting.service.concrete;

import com.example.pagingsorting.dao.entity.CarEntity;
import com.example.pagingsorting.dao.repository.CarRepository;
import com.example.pagingsorting.exception.NotFoundException;
import com.example.pagingsorting.mapper.CarMapper;
import com.example.pagingsorting.model.criteria.CarCriteria;
import com.example.pagingsorting.model.criteria.PageCriteria;
import com.example.pagingsorting.model.request.CarRequest;
import com.example.pagingsorting.model.response.CarResponse;
import com.example.pagingsorting.model.response.PageableCarResponse;
import com.example.pagingsorting.service.abstraction.CarService;
import com.example.pagingsorting.service.specification.CarSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarServiceHandler implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    @Override
    public CarResponse getCar(Long id) {
        var car = fetchCarIfExist(id);
        return carMapper.mapEntityToResponse(car);
    }

    @Override
    public void saveCar(CarRequest carRequest) {
        carRepository.save(carMapper.mapRequestToEntity(carRequest));
    }

    @Override
    public void setColor(Long id, String color) {
        var car = fetchCarIfExist(id);
        car.setColor(color);
        carRepository.save(car);
    }

    @Override
    public PageableCarResponse getCars(PageCriteria pageCriteria, CarCriteria carCriteria) {
        var carPage = carRepository.findAll(
                new CarSpecification(carCriteria),
                PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount(), Sort.by("id").descending())
        );
        return carMapper.mapToPageableResponse(carPage);
    }

    @Override
    public void deleteCar(Long id) {
        var car = fetchCarIfExist(id);
        carRepository.delete(car);
    }

    private CarEntity fetchCarIfExist(Long id){
        return carRepository.findById(id)
                .orElseThrow(
                        ()->{
                    log.error("ActionLog.getCar.error id:{}",id);
                            return new NotFoundException("CAR_NOT_FOUND");
                }
                );
    }

}
