package com.example.facade.impl;

import com.example.dto.CarData;
import com.example.dto.DriverData;
import com.example.entity.Car;
import com.example.entity.Driver;
import com.example.entity.DriverCar;
import com.example.entity.OnlineStatus;
import com.example.exception.CarAlreadyInUseException;
import com.example.exception.ConstraintsViolationException;
import com.example.exception.EntityNotFoundException;
import com.example.facade.DriverFacade;
import com.example.populator.DriverPopulator;
import com.example.service.CarService;
import com.example.service.DriverCarService;
import com.example.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinodjagwani on 7/15/17.
 */
@Service
public class DefaultDriverFacade implements DriverFacade
{

    @Autowired
    private CarService carService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverCarService driverCarService;


    @Override public DriverData findByDriverId(Long driverId) throws EntityNotFoundException
    {
        return DriverPopulator.makeDriverDTO(driverService.findByDriverId(driverId));
    }


    @Override public DriverData create(DriverData driverData) throws ConstraintsViolationException
    {
        Driver driver = DriverPopulator.makeDriverDO(driverData);
        return DriverPopulator.makeDriverDTO(driverService.create(driver));
    }


    @Override public void delete(Long driverId) throws EntityNotFoundException
    {
        driverService.delete(driverId);
    }


    @Override public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @Override public List<DriverData> findByOnlineStatus(OnlineStatus onlineStatus)
    {
        return DriverPopulator.makeDriverDTOList(driverService.findByOnlineStatus(onlineStatus));
    }


    @Override public DriverData selectCarByDriver(Long driverId, Long carId) throws CarAlreadyInUseException, EntityNotFoundException
    {
        Object[] object = new Object[2];
        Driver driver;
        Car car;
        try
        {
            driver = driverService.findByDriverId(driverId);
            car = carService.findCarById(carId);
            if (null != driver && null != car && OnlineStatus.ONLINE.equals(driver.getOnlineStatus()))
            {
                DriverCar driverCar = new DriverCar();
                driverCar.setDriverId(driver.getId());
                driverCar.setCarId(car.getId());
                driverCarService.save(driverCar);
                object[0] = driver;
                object[1] = car;
            }
            else if (null != driver && null != car && OnlineStatus.OFFLINE.equals(driver.getOnlineStatus()))
            {
                throw new CarAlreadyInUseException("Driver is offline");
            }
        }
        catch (EntityNotFoundException e)
        {
            throw new EntityNotFoundException("Car or Driver entity not found ");
        }
        catch (DataIntegrityViolationException e)
        {
            throw new CarAlreadyInUseException("Car is already taken by driver");
        }
        return DriverPopulator.makeDriverDTO(object);

    }


    @Override public void deSelectCarByDriver(Long driverId, Long carId) throws CarAlreadyInUseException, EntityNotFoundException
    {
        Driver driver;
        Car car;
        try
        {
            driver = driverService.findByDriverId(driverId);
            car = carService.findCarById(carId);
            if (null != driver && null != car && OnlineStatus.ONLINE.equals(driver.getOnlineStatus()))
            {
                DriverCar driverCar = driverCarService.findByDriverIdAndCarId(driver.getId(), car.getId());
                driverCarService.delete(driverCar);
            }
        }
        catch (EntityNotFoundException e)
        {
            throw new EntityNotFoundException("Car or Driver entity not found ");
        }
        catch (InvalidDataAccessApiUsageException e)
        {
            throw new CarAlreadyInUseException("Car is already deselected by the driver");
        }
    }


    @Override public List<DriverData> findDriverByCarAttributes(CarData carData)
    {
        List<DriverData> driverDataList = new ArrayList<>();
        List<Object[]> drivers = driverCarService.findDriverByCarAttributes(carData);
        drivers.forEach(object -> driverDataList.add(DriverPopulator.makeDriverDTO(object)));
        return driverDataList;
    }
}
