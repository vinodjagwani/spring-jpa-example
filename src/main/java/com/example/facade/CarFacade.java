package com.example.facade;

import com.example.dto.CarData;
import com.example.exception.EntityNotFoundException;

import java.util.List;

/**
 * Created by vinodjagwani on 7/15/17.
 */
public interface CarFacade
{

    CarData findCarById(final Long carId) throws EntityNotFoundException;

    List<CarData> findAllCars();

    CarData create(final CarData carData) throws EntityNotFoundException;

    void update(final CarData carData) throws EntityNotFoundException;

    void delete(final Long carId) throws EntityNotFoundException;
}
