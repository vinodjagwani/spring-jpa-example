package com.example.facade.impl;

import com.example.dto.CarData;
import com.example.entity.Car;
import com.example.exception.EntityNotFoundException;
import com.example.facade.CarFacade;
import com.example.populator.CarPopulator;
import com.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCarFacade implements CarFacade
{

    @Autowired
    private CarService carService;


    @Override
    public CarData findCarById(Long carId) throws EntityNotFoundException
    {
        return CarPopulator.populate(carService.findCarById(carId));
    }


    @Override
    public List<CarData> findAllCars()
    {
        return CarPopulator.populate(carService.findAllCars());
    }


    @Override
    public CarData create(CarData carData) throws EntityNotFoundException
    {
        Car car = CarPopulator.convert(carData);
        return CarPopulator.populate(carService.create(car));
    }


    @Override
    public void update(final CarData carData) throws EntityNotFoundException
    {
        Car car = CarPopulator.convert(carData);
        car.setId(carData.getId());
        carService.update(car);
    }


    @Override
    public void delete(Long carId) throws EntityNotFoundException
    {
        carService.delete(carId);
    }
}
