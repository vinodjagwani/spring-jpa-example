package com.example.service;

import com.example.entity.Car;
import com.example.exception.EntityNotFoundException;

/**
 * Created by vinodjagwani on 7/15/17.
 */
public interface CarService
{

    Car findCarById(final Long carId) throws EntityNotFoundException;

    Iterable<Car> findAllCars();

    Car create(final Car car) throws EntityNotFoundException;

    void update(final Car car) throws EntityNotFoundException;

    void delete(final Long carId) throws EntityNotFoundException;


}
