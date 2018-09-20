package com.example.service.impl;

import com.example.dto.CarData;
import com.example.entity.DriverCar;
import com.example.repository.DriverCarRepository;
import com.example.service.DriverCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vinodjagwani on 7/15/17.
 */
@Service
public class DefaultDriverCarService implements DriverCarService
{

    @Autowired
    private DriverCarRepository driverCarRepository;


    @Override public void delete(DriverCar driverCar)
    {
        driverCarRepository.delete(driverCar);
    }


    @Override public DriverCar save(DriverCar driverCar)
    {
        return driverCarRepository.save(driverCar);
    }


    @Override public DriverCar findByDriverIdAndCarId(Long driverId, Long carId)
    {
        return driverCarRepository.findByDriverIdAndCarId(driverId, carId);
    }


    @Override
    public List<Object[]> findDriverByCarAttributes(CarData carData)
    {
        return driverCarRepository.findDriverByCarAttributes(carData);
    }
}
