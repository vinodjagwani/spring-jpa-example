package com.example.service.impl;

import com.example.entity.Car;
import com.example.entity.Manufacturer;
import com.example.exception.EntityNotFoundException;
import com.example.repository.CarRepository;
import com.example.repository.ManufacturerRepository;
import com.example.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vinodjagwani on 7/15/17.
 */
@Slf4j
@Service
public class DefaultCarService implements CarService
{

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;


    @Override
    public Car findCarById(final Long carId) throws EntityNotFoundException
    {
        return carCheck(carId);
    }


    @Override
    public Iterable<Car> findAllCars()
    {
        return carRepository.findAll();
    }


    @Override
    public Car create(final Car car) throws EntityNotFoundException
    {
        car.setManufacturer(manufacturerCheck(car));
        return carRepository.save(car);
    }


    @Override
    @Transactional
    public void update(final Car car) throws EntityNotFoundException
    {
        Car updateCar = carCheck(car.getId());
        updateCar.setManufacturer(manufacturerCheck(car));
        updateCar.setConvertible(car.getConvertible());
        updateCar.setEngineType(car.getEngineType());
        updateCar.setLicensePlate(car.getLicensePlate());
        updateCar.setRating(car.getRating());
        updateCar.setSeatCount(car.getSeatCount());
    }


    @Override
    @Transactional
    public void delete(final Long carId) throws EntityNotFoundException
    {
        Car car = carCheck(carId);
        car.setDeleted(Boolean.TRUE);
    }


    private Car carCheck(final Long carId) throws EntityNotFoundException
    {
        Car car = carRepository.findOne(carId);
        if (null == car)
        {
            throw new EntityNotFoundException("Could not find car entity with id: " + carId);
        }
        return car;
    }


    private Manufacturer manufacturerCheck(final Car car) throws EntityNotFoundException
    {
        String manufacturerName = car.getManufacturer().getName();
        Manufacturer manufacturer = manufacturerRepository.findByName(manufacturerName);
        if (null == manufacturer)
        {
            throw new EntityNotFoundException("Manufacturer not found with this name: " + manufacturerName);
        }
        return manufacturer;
    }
}
