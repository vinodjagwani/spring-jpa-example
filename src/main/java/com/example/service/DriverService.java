package com.example.service;

import com.example.entity.Driver;
import com.example.entity.OnlineStatus;
import com.example.exception.ConstraintsViolationException;
import com.example.exception.EntityNotFoundException;
import java.util.List;

public interface DriverService
{

    Driver findByDriverId(Long driverId) throws EntityNotFoundException;

    Driver create(Driver driver) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<Driver> findByOnlineStatus(OnlineStatus onlineStatus);

}
