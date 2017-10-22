package com.mytaxi.controller.mapper;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;

import java.util.List;
import java.util.stream.Collectors;

public class CarMapper
{
    public static CarDO makeCarDO(CarDTO carDTO)
    {
        return new CarDO(carDTO.getLicensePlate(),
                carDTO.getSeatCount(), carDTO.isConvertible(),
                carDTO.getEngineType(), carDTO.getRating(),
                ManufacturerMapper.makeManufacturerDO(carDTO.getManufacturer()));
    }

    public static CarDTO makeCarDTO(CarDO carDO)
    {
        CarDTO.CarDTOBuilder carDTOBuilder = new CarDTO.CarDTOBuilder()
                .setId(carDO.getId())
                .setConvertible(carDO.getConvertible())
                .setEngineType(carDO.getEngineType())
                .setLicensePlate(carDO.getLicensePlate())
                .setRating(carDO.getRating())
                .setSeatCount(carDO.getSeatCount());
        if (carDO.getManufacturer() != null) {
            carDTOBuilder
                    .setManufacturer(ManufacturerMapper
                            .makeManufacturerDTO(carDO.getManufacturer()));
        }
        return carDTOBuilder.createCarDTO();
    }

    public static List<CarDTO> makeCarDTOList(List<CarDO> cars)
    {
        return cars.stream()
                .map(CarMapper::makeCarDTO)
                .collect(Collectors.toList());
    }
}
