package com.example.controller;

import com.example.dto.CarData;
import com.example.exception.EntityNotFoundException;
import com.example.facade.CarFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * All operations with a car will be routed by this controller.
 * Created by vinodjagwani on 7/15/17.
 * <p/>
 */
@RestController
@RequestMapping("v1/cars")
public class CarController
{

    @Autowired
    private CarFacade carFacade;


    @RequestMapping(value = "/{carId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CarData> getCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        return new ResponseEntity<>(carFacade.findCarById(carId), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CarData>> getAllCars()
    {
        return new ResponseEntity<>(carFacade.findAllCars(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CarData> createCar(@Valid @RequestBody CarData carData) throws EntityNotFoundException
    {
        return new ResponseEntity<>(carFacade.create(carData), HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateCar(@Valid @RequestBody CarData carData) throws EntityNotFoundException
    {
        carFacade.update(carData);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/{carId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        carFacade.delete(carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
