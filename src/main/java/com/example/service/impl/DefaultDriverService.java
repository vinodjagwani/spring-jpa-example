package com.example.service.impl;

import com.example.repository.DriverRepository;
import com.example.entity.Driver;
import com.example.entity.GeoCoordinate;
import com.example.entity.OnlineStatus;
import com.example.exception.ConstraintsViolationException;
import com.example.exception.EntityNotFoundException;

import java.util.List;

import com.example.service.DriverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Slf4j
@Service
public class DefaultDriverService implements DriverService
{

    @Autowired
    private DriverRepository driverRepository;


    @Override
    public Driver findByDriverId(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    @Override
    public Driver create(Driver driverDO) throws ConstraintsViolationException
    {
        Driver driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            log.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        Driver driver = findDriverChecked(driverId);
        driver.setDeleted(true);
    }


    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        Driver driver = findDriverChecked(driverId);
        driver.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    @Override
    public List<Driver> findByOnlineStatus(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }


    private Driver findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        Driver driver = driverRepository.findOne(driverId);
        if (driver == null)
        {
            throw new EntityNotFoundException("Could not find entity with id: " + driverId);
        }
        return driver;
    }

}
