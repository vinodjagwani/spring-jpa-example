package com.example.controller;

import com.example.dto.CarData;
import com.example.exception.CarAlreadyInUseException;
import com.example.facade.DriverFacade;
import com.example.dto.DriverData;
import com.example.entity.OnlineStatus;
import com.example.exception.ConstraintsViolationException;
import com.example.exception.EntityNotFoundException;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.commons.collections.MapUtils.*;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{

    @Autowired
    private DriverFacade driverFacade;


    @PostMapping("/select")
    public DriverData selectCarByDriver(@RequestParam long driverId, @RequestParam long carId) throws EntityNotFoundException,
        CarAlreadyInUseException
    {

        return driverFacade.selectCarByDriver(driverId, carId);
    }


    @DeleteMapping("/deselect")
    public void deSelectCarByDriver(@RequestParam long driverId, @RequestParam long carId) throws EntityNotFoundException,
        CarAlreadyInUseException
    {
        driverFacade.deSelectCarByDriver(driverId, carId);
    }


    @GetMapping("/{driverId}")
    public DriverData getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        return driverFacade.findByDriverId(driverId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverData createDriver(@Valid @RequestBody DriverData driverData) throws ConstraintsViolationException
    {
        return driverFacade.create(driverData);
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        driverFacade.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
        @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws EntityNotFoundException
    {
        driverFacade.updateLocation(driverId, longitude, latitude);
    }


    @GetMapping
    public List<DriverData> findDrivers(@RequestParam OnlineStatus onlineStatus)
        throws ConstraintsViolationException
    {
        return driverFacade.findByOnlineStatus(onlineStatus);
    }


    @GetMapping("/car")
    public List<DriverData> findDriverByCarAttributes(@RequestParam Map<String, String> params)
    {
        return driverFacade.findDriverByCarAttributes(getCarDataRequest(params));
    }


    private CarData getCarDataRequest(Map<String, String> params)
    {
        return CarData.builder()
            .seatCount(getInteger(params, "seatCount"))
            .engineType(getString(params, "engineType"))
            .convertible(getBoolean(params, "convertible"))
            .build();
    }
}
