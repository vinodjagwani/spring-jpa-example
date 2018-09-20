package com.example.service;

import com.example.dto.CarData;
import com.example.entity.DriverCar;

import java.util.List;

/**
 * Created by vinodjagwani on 7/15/17.
 */
public interface DriverCarService
{

    void delete(DriverCar driverCar);

    DriverCar  save(DriverCar driverCar);

    DriverCar findByDriverIdAndCarId(final Long driverId, final Long carId);

    List<Object[]> findDriverByCarAttributes(final CarData carData);

}
