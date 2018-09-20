package com.example.facade;

import com.example.dto.CarData;
import com.example.dto.DriverData;
import com.example.entity.OnlineStatus;
import com.example.exception.CarAlreadyInUseException;
import com.example.exception.ConstraintsViolationException;
import com.example.exception.EntityNotFoundException;

import java.util.List;

/**
 * Created by vinodjagwani on 7/15/17.
 */
public interface DriverFacade
{

    DriverData findByDriverId(Long driverId) throws EntityNotFoundException;

    DriverData create(DriverData driverData) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverData> findByOnlineStatus(OnlineStatus onlineStatus);

    DriverData selectCarByDriver(Long driverId, Long carId) throws EntityNotFoundException, CarAlreadyInUseException;

    void deSelectCarByDriver(Long driverId, Long carId) throws EntityNotFoundException, CarAlreadyInUseException;

    List<DriverData> findDriverByCarAttributes(final CarData carData);


}
