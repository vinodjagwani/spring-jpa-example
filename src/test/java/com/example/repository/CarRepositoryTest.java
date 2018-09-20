package com.example.repository;

import com.google.common.collect.Lists;
import com.example.entity.Car;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

/**
 * Created by vinodjagwani on 7/15/17.
 */
public class CarRepositoryTest extends AbstractRepositoryTest
{

    @Autowired
    private CarRepository carRepository;


    @Test
    public void testDriverById()
    {
        Car car = carRepository.findOne(1L);
        Assert.assertNotNull(car);
    }


    @Test
    public void testAllCars()
    {
        List<Car> cars = Lists.newArrayList(carRepository.findAll());
        Assert.assertThat(cars, hasSize(3));
    }

}
