package com.example.pagingsorting.service.specification;

import com.example.pagingsorting.dao.entity.CarEntity;
import com.example.pagingsorting.model.criteria.CarCriteria;
import com.example.pagingsorting.service.util.PredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static com.example.pagingsorting.model.constants.CriteriaConstants.*;
import static com.example.pagingsorting.service.util.PredicateUtil.applyLikePattern;

@AllArgsConstructor
public class CarSpecification implements Specification<CarEntity> {
    private CarCriteria carCriteria;

    @Override
    public Predicate toPredicate(Root<CarEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        var predicates = PredicateUtil.builder()
                .addNullSafety(carCriteria.getColor(), color -> criteriaBuilder.equal(root.get(COLOR), color))
                .addNullSafety(carCriteria.getBrand(), brand -> criteriaBuilder.like(root.get(BRAND), applyLikePattern(brand)))
                .addNullSafety(carCriteria.getModel(), model -> criteriaBuilder.like(root.get(MODEL), applyLikePattern(model)))
                .addNullSafety(carCriteria.getYearFrom(), yearFrom -> criteriaBuilder.greaterThanOrEqualTo(root.get(PRODUCTION_YEAR), yearFrom))
                .addNullSafety(carCriteria.getYearTo(), yearTo -> criteriaBuilder.lessThanOrEqualTo(root.get(PRODUCTION_YEAR), yearTo))
                .build();
        return criteriaBuilder.and(predicates);
    }
}