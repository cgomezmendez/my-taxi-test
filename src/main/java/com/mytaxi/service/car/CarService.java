package com.mytaxi.service.car;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

import java.util.List;

public interface CarService
{
    CarDO find(Long carId) throws EntityNotFoundException;

    CarDO create(CarDO carDO) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

    List<CarDO> find(Boolean convertible, EngineType engineType, String manufacturer, Integer rating, Integer seatCount, String licensePlate);

    void updateRating(Long carId, int rating) throws EntityNotFoundException;
}
