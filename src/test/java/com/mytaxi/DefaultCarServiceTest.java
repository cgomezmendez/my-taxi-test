package com.mytaxi;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.datatransferobject.ManufacturerDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.DefaultCarService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.dao.DataIntegrityViolationException;

public class DefaultCarServiceTest
{
    @Mock
    CarRepository carRepository;

    @InjectMocks
    DefaultCarService defaultCarService;

    @Captor
    ArgumentCaptor<CarDO> carArgumentCaptor;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void creatingACarTellsTheRepositoryToSaveACar() throws ConstraintsViolationException
    {
        CarDO carToSave = new CarDO("ABCD-1234",
                4, false, EngineType.GAS, 100,
                new ManufacturerDO("Tesla"));

        CarDO savedCar = new CarDO("ABCD-1234",
                4, false, EngineType.GAS, 100,
                new ManufacturerDO("Tesla"));
        savedCar.setId(1L);
        Mockito.when(carRepository.save(carArgumentCaptor.capture()))
        .thenReturn(savedCar).getMock();

        CarDO carResponse = defaultCarService.create(carToSave);
        Assert.assertEquals(carResponse, savedCar);
        Assert.assertEquals(carArgumentCaptor.getValue(), carToSave);

    }

    @Test(expected = ConstraintsViolationException.class)
    public void creatingACallWithDuplicatedUniqueConstraintsThrowsException() throws ConstraintsViolationException
    {
        CarDO carToSave = new CarDO("ABCD-1234",
                4, false, EngineType.GAS, 100,
                new ManufacturerDO("Tesla"));

        Mockito.when(carRepository.save(carToSave))
                .thenThrow(DataIntegrityViolationException.class);

        defaultCarService.create(carToSave);
    }

    @Test
    public void findingAndExistingCarById() throws EntityNotFoundException
    {
        CarDO existingCar = new CarDO("ABCD-1234",
                4, false, EngineType.GAS, 100,
                new ManufacturerDO("Tesla"));
        existingCar.setId(1L);

        Mockito.when(carRepository.findOne(1L))
                .thenReturn(existingCar);
        CarDO carResponse = defaultCarService.find(1L);

        Assert.assertEquals(carResponse, existingCar);
    }

    @Test(expected = EntityNotFoundException.class)
    public void raiseEntityNotFoundExceptionWhenUnableToFindExistingCar() throws EntityNotFoundException
    {
        Mockito.when(carRepository.findOne(1L))
                .thenReturn(null);

        defaultCarService.find(1L);
    }

    @Test
    public void deletingACarChangeDeletedFieldToTrue() throws EntityNotFoundException
    {
        CarDO carToBeDeleted = new CarDO("ABCD-1234",
                4, false, EngineType.GAS, 100,
                new ManufacturerDO("Tesla"));
        carToBeDeleted.setId(1L);

        Mockito.when(carRepository.findOne(1L))
                .thenReturn(carToBeDeleted);

        defaultCarService.delete(1L);

        Assert.assertTrue(carToBeDeleted.getDeleted());
    }

    @Test(expected = EntityNotFoundException.class)
    public void deletingANoExistingCarThrowsAnException() throws EntityNotFoundException
    {
        Mockito.when(carRepository.findOne(1L))
                .thenReturn(null);

        defaultCarService.delete(1L);
    }
}
