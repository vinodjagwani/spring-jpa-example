package com.example.populator;

import com.example.dto.CarData;
import com.example.entity.Car;
import com.example.entity.Manufacturer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinodjagwani on 7/15/17.
 */
public class CarPopulator
{

    public static CarData populate(Car source)
    {
        return CarData.builder()
            .id(source.getId())
            .convertible(source.getConvertible())
            .engineType(source.getEngineType())
            .licensePlate(source.getLicensePlate())
            .manufacturer(source.getManufacturer().getName())
            .rating(source.getRating())
            .seatCount(source.getSeatCount())
            .build();
    }


    public static List<CarData> populate(Iterable<Car> source)
    {
        List<CarData> carDataList = new ArrayList<>();
        source.forEach(car -> carDataList.add(populate(car)));
        return carDataList;
    }


    public static Car convert(CarData source)
    {
        Car car = new Car();
        car.setConvertible(source.getConvertible());
        car.setEngineType(source.getEngineType());
        car.setLicensePlate(source.getLicensePlate());
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(source.getManufacturer());
        car.setManufacturer(manufacturer);
        car.setRating(source.getRating());
        car.setSeatCount(source.getSeatCount());
        return car;

    }

}
