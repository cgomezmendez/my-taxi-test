package com.mytaxi.controller;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.CarRatingDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.util.List;

@RestController
@RequestMapping("v1/cars")

/**
 * All operations with a car will be routed by this controller.
 * <p/>
 */
public class CarController
{
    private final CarService carService;

    @Autowired
    public CarController(final CarService carService)
    {
        this.carService = carService;
    }

    @GetMapping("/{carId}")
    public CarDTO getCar(@Valid @PathVariable long carId) throws EntityNotFoundException {
        return CarMapper.makeCarDTO(carService.find(carId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) {
        CarDO carDO = CarMapper.makeCarDO(carDTO);
        return CarMapper.makeCarDTO(carDO);
    }

    @DeleteMapping("/{carId}")
    public void deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException {
        carService.delete(carId);
    }

    @GetMapping
    public List<CarDTO> findCars(@RequestParam(required = false) Boolean convertible,
                                 @RequestParam(required = false) EngineType engineType,
                                 @RequestParam(required = false) String manufacturer,
                                 @RequestParam(required = false) Integer seatCount,
                                 @RequestParam(required = false) Integer rating,
                                 @RequestParam(required = false) String licensePlate)
    {
        return CarMapper.makeCarDTOList(carService
                .find(convertible,
                        engineType, manufacturer,
                        rating, seatCount, licensePlate));
    }

    @PutMapping("/{carId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rating", paramType = "query",
            required = true)
    })
    public void updateRating(
            @Valid @PathVariable long carId,
            @ModelAttribute("rating")
            @Valid CarRatingDTO rating)
            throws ConstraintsViolationException, EntityNotFoundException
    {
        carService.updateRating(carId, rating.getRating());
    }
}
