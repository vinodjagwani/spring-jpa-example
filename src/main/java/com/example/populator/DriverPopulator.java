package com.example.populator;

import com.example.dto.CarData;
import com.example.dto.DriverData;
import com.example.entity.Car;
import com.example.entity.Driver;
import com.example.entity.GeoCoordinate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DriverPopulator
{
    public static Driver makeDriverDO(DriverData driverData)
    {
        return new Driver(driverData.getUsername(), driverData.getPassword());
    }


    public static DriverData makeDriverDTO(Object[] object)
    {
        Driver driver = (Driver) object[0];
        Car car =  (Car) object[1] ;
        CarData carData = CarData.builder()
            .id(car.getId())
            .rating(car.getRating())
            .seatCount(car.getSeatCount())
            .engineType(car.getEngineType())
            .convertible(car.getConvertible())
            .licensePlate(car.getLicensePlate())
            .manufacturer(car.getManufacturer().getName())
            .build();
        DriverData carDriverData = makeDriverDTO(driver);
        carDriverData.setCarData(carData);
        return carDriverData;
    }


    public static DriverData makeDriverDTO(Driver driver)
    {
        DriverData.DriverDTOBuilder driverDTOBuilder = DriverData.newBuilder()
            .setId(driver.getId())
            .setPassword(driver.getPassword())
            .setUsername(driver.getUsername());

        GeoCoordinate coordinate = driver.getCoordinate();
        if (coordinate != null)
        {
            driverDTOBuilder.setCoordinate(coordinate);
        }

        return driverDTOBuilder.createDriverDTO();
    }


    public static List<DriverData> makeDriverDTOList(Collection<Driver> drivers)
    {
        return drivers.stream()
            .map(DriverPopulator::makeDriverDTO)
            .collect(Collectors.toList());
    }
}
