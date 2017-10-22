package com.mytaxi;

import com.mytaxi.controller.CarController;
import com.mytaxi.controller.DriverController;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import com.mytaxi.service.driver.DriverService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Driver;

public class DriverControllerTest
{
    @InjectMocks
    DriverController driverController;

    @Mock
    DriverService driverService;

    @Mock
    DriverRepository driverRepository;

    @Mock
    CarService carService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    MockMvc mockMvc;

    @Before
    public void setUpMockMvc() {
        mockMvc = MockMvcBuilders.standaloneSetup(driverController)
                .build();
    }

    @Test
    public void driverCanSelectACar() throws Exception
    {
        CarDO carDO = Mockito.when(Mockito.mock(CarDO.class).getId())
                .thenReturn(1L)
                .getMock();

        Mockito.when(carService.find(1L))
                .thenReturn(carDO);

        mockMvc.perform(
                MockMvcRequestBuilders.put(
                        "/v1/drivers/1/selected_car")
                        .param("car_id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
