package com.mytaxi;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import com.mytaxi.service.car.DefaultCarService;
import com.mytaxi.service.driver.DefaultDriverService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

public class DefaultDriverServiceTest
{
    @Mock
    DriverRepository driverRepository;

    @Mock
    CarService carService;

    @InjectMocks
    DefaultDriverService defaultDriverService;

    @Captor
    ArgumentCaptor<DriverDO> driverArgumentCaptor;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void updatingSelectedCarUpdateField() throws EntityNotFoundException, CarAlreadyInUseException
    {
        CarDO carDO = Mockito.when(Mockito.mock(CarDO.class).getId())
                .thenReturn(1L)
                .getMock();
        Mockito.when(carService.find(1L))
                .thenReturn(carDO);

        DriverDO driverDo = new DriverDO("test", "test");

        Mockito.when(driverRepository.findOne(1L))
                .thenReturn(driverDo);

        Mockito.when(driverRepository.save(driverArgumentCaptor.capture()))
        .thenReturn(driverDo);

        defaultDriverService.updateSelectedCar(1L, carDO);
        Assert.assertEquals(carDO, driverDo.getSelectedCar());
    }

    @Test(expected = CarAlreadyInUseException.class)
    public void updatingSelectedCarReturnErrorIfCarSelectedByAnotherOnlineDriver()
            throws EntityNotFoundException, CarAlreadyInUseException
    {
        CarDO carDO = Mockito.when(Mockito.mock(CarDO.class).getId())
                .thenReturn(1L)
                .getMock();
        Mockito.when(carService.find(1L))
                .thenReturn(carDO);

        DriverDO driverDo = new DriverDO("test", "test");
        DriverDO driverOnlineUsingCar = new DriverDO("test_2", "test");
        List driversUsingCar = new ArrayList();
        driversUsingCar.add(driversUsingCar);

        Mockito.when(driverRepository.findOne(1L))
                .thenReturn(driverDo);

        Mockito.when(driverRepository.save(driverArgumentCaptor.capture()))
                .thenReturn(driverDo);

        Mockito.when(driverRepository.findByOnlineStatusAndSelectedCar(OnlineStatus.ONLINE,
                carDO))
        .thenReturn(driversUsingCar);

        defaultDriverService.updateSelectedCar(1L, carDO);
    }
}
