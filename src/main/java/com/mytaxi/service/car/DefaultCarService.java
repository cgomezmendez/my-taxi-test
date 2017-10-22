package com.mytaxi.service.car;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.CarSpecifications;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.util.Utils;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some car specific things.
 * <p/>
 */
@Service
public class DefaultCarService implements CarService
{
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultCarService.class);
    private CarRepository carRepository;

    public DefaultCarService(final CarRepository carRepository)
    {
        this.carRepository = carRepository;
    }

    /**
     * Selects a car by id.
     *
     * @param carId
     * @return found car
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    public CarDO find(Long carId) throws EntityNotFoundException
    {
        return findCarChecked(carId);
    }

    /**
     * Creates a new car.
     *
     * @param carDO
     * @return
     * @throws ConstraintsViolationException if a car already exists with the given licensePlate, ...
     */
    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException
    {
        CarDO car;
        try {
            car = carRepository.save(carDO);
        } catch (DataIntegrityViolationException e) {
            LOG.warn("Some constraints are thrown due to car creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return car;
    }

    /**
     * Deletes an existing car by id.
     *
     * @param carId
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long carId) throws EntityNotFoundException
    {
        CarDO carDO = findCarChecked(carId);
        carDO.setDeleted(true);
        carRepository.save(carDO);
    }

    private CarDO findCarChecked(Long carId) throws EntityNotFoundException
    {
        CarDO carDO = carRepository.findOne(carId);

        if (carDO == null) {
            throw new EntityNotFoundException("Could not find entity with id: " + carId);
        }
        return carDO;
    }

    @Override
    public List<CarDO> find(Boolean convertible, EngineType engineType, String manufacturer, Integer rating, Integer seatCount, String licensePlate)
    {
        List<CarDO> carDO = Utils.toList(carRepository.findAll(
                CarSpecifications.withCriteria(convertible,
                        engineType, manufacturer,
                        rating, seatCount, licensePlate)
        ));
        return carDO;
    }

    @Override
    public void updateRating(Long carId, int rating) throws EntityNotFoundException
    {
        CarDO carDO = findCarChecked(carId);
        carDO.setRating(rating);
        carRepository.save(carDO);
    }
}
