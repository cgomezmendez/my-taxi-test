package com.mytaxi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.controller.CarController;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.ManufacturerDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import org.hamcrest.Matchers;
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

import java.util.ArrayList;
import java.util.List;

public class CarControllerTest
{
    @InjectMocks
    CarController carController;

    @Mock
    CarService carService;

    @Mock
    CarRepository carRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    MockMvc mockMvc;

    @Before
    public void setUpMockMvc() {
        mockMvc = MockMvcBuilders.standaloneSetup(carController)
                .build();
    }

    @Test
    public void creatingACarTellTheCarServiceToCreateACall() throws Exception
    {
        CarDO carDO = Mockito.when(Mockito.mock(CarDO.class).getId())
                .thenReturn(1L)
                .getMock();

        Mockito.when(carService.create(Mockito.isA(CarDO.class)))
                .thenReturn(carDO);

        CarDTO carRequest = new CarDTO(null, "ABCD123",
                2, true, EngineType.GAS, 100,
                new ManufacturerDTO(null, "Tesla"));

        mockMvc.perform(
                MockMvcRequestBuilders.post(
                "/v1/cars/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(carRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void returnsErrorWhenTryingToCreateAnInvalidCar() throws Exception
    {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/cars/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void viewingACarAskTheCarServiceForTheCar() throws Exception
    {
        CarDO carDO = Mockito.when(Mockito.mock(CarDO.class).getId())
                .thenReturn(1L)
                .getMock();

        Mockito.when(carService.find(1L))
        .thenReturn(carDO);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/cars/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void returnsNotFoundWhenUnableToFindTheCar() throws Exception
    {
        Mockito.when(carService.find(1L))
        .thenThrow(EntityNotFoundException.class);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/cars/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deletingACarAskTheCarServiceToDeleteIt() throws Exception
    {
        CarDO carDO = Mockito.when(Mockito.mock(CarDO.class).getId())
                .thenReturn(1L)
                .getMock();
        Mockito.when(carRepository.findOne(1L))
                .thenReturn(carDO);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/v1/cars/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findCarsAskTheCarServiceForAListOfCars() throws Exception
    {
        List<CarDO> carDOList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CarDO carDO = new CarDO("ABCDEFG" + i, 4,
                    false, EngineType.ELECTRIC, 100,
                    new ManufacturerDO("Tesla"));
            carDO.setId(Integer.toUnsignedLong(i));
            carDOList.add(carDO);
        }
        Mockito.when(carService.find(Mockito.anyBoolean(),
                Mockito.any(EngineType.class),
                Mockito.anyString(), Mockito.anyInt(),
                Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(carDOList);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/cars")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",
                        Matchers.hasSize(carDOList.size())));
    }

    @Test
    public void updateCarRating() throws Exception {
        CarDO carDO = Mockito.when(Mockito.mock(CarDO.class).getId())
                .thenReturn(1L)
                .getMock();

        Mockito.when(carService.create(Mockito.isA(CarDO.class)))
                .thenReturn(carDO);

        mockMvc.perform(
                MockMvcRequestBuilders.put(
                        "/v1/cars/1")
                        .accept(MediaType.APPLICATION_JSON)
                .param("rating", "50"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateCarRatingShouldGetErrorIfInvalid() throws Exception {
        CarDO carDO = Mockito.when(Mockito.mock(CarDO.class).getId())
                .thenReturn(1L)
                .getMock();

        Mockito.when(carService.create(Mockito.isA(CarDO.class)))
                .thenReturn(carDO);

        mockMvc.perform(
                MockMvcRequestBuilders.put(
                        "/v1/cars/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("rating", "-2"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
