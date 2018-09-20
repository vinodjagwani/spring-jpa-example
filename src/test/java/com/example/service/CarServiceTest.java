package com.example.service;

import com.example.AbstractTest;
import com.example.entity.Car;
import com.example.entity.Manufacturer;
import com.example.exception.EntityNotFoundException;
import com.example.repository.CarRepository;
import com.example.repository.ManufacturerRepository;
import com.example.service.impl.DefaultCarService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

import java.util.Collections;

/**
 * Created by vinodjagwani on 7/15/17.
 */
public class CarServiceTest extends AbstractTest
{

    @Mock
    private CarRepository carRepository;

    @Mock
    private ManufacturerRepository manufacturerRepository;

    @InjectMocks
    private DefaultCarService carService;


    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(DefaultCarService.class);
    }


    @Test
    public void testFindCarById() throws EntityNotFoundException
    {
        Car car = getCar();
        when(carRepository.findOne(any(Long.class))).thenReturn(car);
        carService.findCarById(any(Long.class));
        verify(carRepository, times(1)).findOne(any(Long.class));
    }


    @Test
    public void testFindAllCars()
    {
        Iterable<Car> cars = Collections.singletonList(getCar());
        when(carRepository.findAll()).thenReturn(cars);
        carService.findAllCars();
        verify(carRepository, times(1)).findAll();
    }


    @Test
    public void testCreate() throws EntityNotFoundException
    {
        Car car = getCar();
        Manufacturer manufacturer = getManufacturer();
        when(manufacturerRepository.findByName(any(String.class))).thenReturn(manufacturer);
        when(carRepository.save(any(Car.class))).thenReturn(car);
        carService.create(car);
        verify(manufacturerRepository, times(1)).findByName(any(String.class));
        verify(carRepository, times(1)).save(car);
    }


    @Test
    public void testUpdate() throws Exception
    {
        Car car = getCar();
        Manufacturer manufacturer = getManufacturer();
        when(carRepository.findOne(any(Long.class))).thenReturn(car);
        when(manufacturerRepository.findByName(any(String.class))).thenReturn(manufacturer);
        carService.update(car);
        verify(carRepository, times(1)).findOne(any(Long.class));
        verify(manufacturerRepository, times(1)).findByName(any(String.class));
    }


    @Test
    public void testDelete() throws EntityNotFoundException
    {
        Car car = getCar();
        when(carRepository.findOne(any(Long.class))).thenReturn(car);
        carService.delete(1L);
        verify(carRepository, times(1)).findOne(any(Long.class));
    }
}

