package com.example;

import com.example.dto.CarData;
import com.example.dto.DriverData;
import com.example.entity.*;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.ZonedDateTime;

/**
 * Created by vinodjagwani on 7/15/17.
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractTest
{

    public Car getCar()
    {
        Car car = new Car();
        car.setId(1L);
        car.setSeatCount(2);
        car.setRating(11.0F);
        car.setDateCreated(ZonedDateTime.now());
        car.setLicensePlate("ABV101");
        car.setEngineType("test");
        car.setConvertible(true);
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Audi");
        manufacturer.setId(1L);
        manufacturer.setDateCreated(ZonedDateTime.now());
        car.setManufacturer(manufacturer);
        return car;
    }


    public Manufacturer getManufacturer()
    {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setDateCreated(ZonedDateTime.now());
        manufacturer.setId(1L);
        manufacturer.setName("Audi");
        return manufacturer;
    }


    public CarData getCarData()
    {
        return CarData.builder()
            .convertible(true)
            .engineType("test")
            .licensePlate("ABV101")
            .seatCount(2)
            .manufacturer("Audi")
            .rating(11.0F)
            .build();
    }


    public Driver getDriver()
    {
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setDateCreated(ZonedDateTime.now());
        driver.setDeleted(false);
        driver.setUsername("test");
        driver.setPassword("test");
        driver.setOnlineStatus(OnlineStatus.ONLINE);
        GeoCoordinate geoCoordinate = new GeoCoordinate(90, 90);
        driver.setCoordinate(geoCoordinate);
        return driver;
    }


    public DriverData getDriverData()
    {
        GeoCoordinate geoCoordinate = new GeoCoordinate(90, 90);
        return DriverData.newBuilder()
            .setId(1L)
            .setPassword("test")
            .setUsername("test")
            .setCoordinate(geoCoordinate)
            .createDriverDTO();
    }


    public DriverCar getDriverCar()
    {
        DriverCar driverCar = new DriverCar();
        driverCar.setCarId(1L);
        driverCar.setDriverId(1L);
        driverCar.setCarId(1L);
        return driverCar;
    }
}
