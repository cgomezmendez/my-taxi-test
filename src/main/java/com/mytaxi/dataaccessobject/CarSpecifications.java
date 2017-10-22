package com.mytaxi.dataaccessobject;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.CarDO_;
import com.mytaxi.domainobject.ManufacturerDO_;
import com.mytaxi.domainvalue.EngineType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CarSpecifications
{

    public static Specification<CarDO> withCriteria(Boolean convertible,
                                                    EngineType engineType,
                                                    String manufacturer,
                                                    Integer rating,
                                                    Integer seatCount,
                                                    String licensePlate)
    {
        return (root, criteriaQuery,
                criteriaBuilder) -> {
            final Collection<Predicate> predicates = new ArrayList<>();
            if (convertible != null) {
                predicates.add(criteriaBuilder.equal(root.get(CarDO_.convertible),
                        convertible));
            }

            if (engineType != null) {
                predicates.add(criteriaBuilder.equal(root.get(CarDO_.engineType),
                        engineType));
            }

            if (manufacturer != null) {
                predicates.add(criteriaBuilder.equal(root.join(CarDO_.manufacturer)
                                .get(ManufacturerDO_.name),
                        manufacturer));
            }

            if (rating != null) {
                predicates.add(criteriaBuilder.equal(root.get(CarDO_.rating),
                        rating));
            }

            if (seatCount != null) {
                predicates.add(criteriaBuilder.equal(root.get(CarDO_.seatCount),
                        seatCount));
            }

            if (licensePlate != null) {
                predicates.add(criteriaBuilder.equal(root.get(CarDO_.licensePlate),
                        licensePlate));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
