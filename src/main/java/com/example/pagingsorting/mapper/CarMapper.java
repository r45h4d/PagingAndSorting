package com.example.pagingsorting.mapper;

import com.example.pagingsorting.dao.entity.CarEntity;
import com.example.pagingsorting.model.request.CarRequest;
import com.example.pagingsorting.model.response.CarResponse;
import com.example.pagingsorting.model.response.PageableCarResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class CarMapper {
    public CarEntity mapRequestToEntity(CarRequest carRequest){
        return CarEntity.builder()
                .brand(carRequest.getBrand())
                .model(carRequest.getModel())
                .color(carRequest.getColor())
                .productionYear(carRequest.getProductionYear())
                .build();
    }

    public CarResponse mapEntityToResponse(CarEntity carEntity){
        return CarResponse.builder()
                .id(carEntity.getId())
                .brand(carEntity.getBrand())
                .model(carEntity.getModel())
                .color(carEntity.getColor())
                .productionYear(carEntity.getProductionYear())
                .build();
    }

    public PageableCarResponse mapToPageableResponse(Page<CarEntity> carEntityPage) {
        List<CarResponse> carResponses = carEntityPage.getContent().stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());

        return PageableCarResponse.builder()
                .cars(carResponses)
                .lastPageNumber(carEntityPage.getTotalPages() - 1)
                .totalElements(carEntityPage.getTotalElements())
                .hasNextPage(carEntityPage.hasNext())
                .build();
    }
}
