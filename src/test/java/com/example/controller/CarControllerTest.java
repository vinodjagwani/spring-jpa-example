package com.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.AbstractTest;
import com.example.dto.CarData;
import com.example.facade.CarFacade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Created by vinodjagwani on 7/15/17.
 */

public class CarControllerTest extends AbstractTest
{

    private static final ObjectMapper mapper = new ObjectMapper();

    private MockMvc mvc;

    @Mock
    private CarFacade carFacade;

    @InjectMocks
    private CarController carController;


    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(CarController.class);
    }


    @Before
    public void init()
    {
        mvc = MockMvcBuilders.standaloneSetup(carController).dispatchOptions(true).build();
    }


    @Test
    public void testGetCar() throws Exception
    {
        CarData carData = getCarData();
        doReturn(carData).when(carFacade).findCarById(any(Long.class));
        carController.getCar(1L);
        MvcResult result = mvc
            .perform(get("/v1/cars/{carId}", 1))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("ABV101"));
    }


    @Test
    public void getAllCars() throws Exception
    {
        List<CarData> cars = Collections.singletonList(getCarData());
        doReturn(cars).when(carFacade).findAllCars();
        carController.getAllCars();
        MvcResult result = mvc
            .perform(get("/v1/cars"))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("ABV101"));
    }


    @Test
    public void createCar() throws Exception
    {
        CarData carData = getCarData();
        String jsonInString = mapper.writeValueAsString(carData);
        doReturn(carData).when(carFacade).create(any(CarData.class));
        carController.createCar(carData);
        MvcResult result = mvc
            .perform(post("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonInString))
            .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("11.0"));
    }


    @Test
    public void updateCar() throws Exception
    {
        CarData carData = getCarData();
        String jsonInString = mapper.writeValueAsString(carData);
        doNothing().when(carFacade).update(any(CarData.class));
        carController.updateCar(carData);
        MvcResult result = mvc
            .perform(put("/v1/cars")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(jsonInString))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }


    @Test
    public void deleteCar() throws Exception
    {
        doNothing().when(carFacade).delete(any(Long.class));
        carController.deleteCar(1L);
        MvcResult result = mvc
            .perform(delete("/v1/cars/{carId}", 1L))
            .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

}
