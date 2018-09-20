package com.example.repository;

import com.example.dto.CarData;
import com.example.entity.Car;
import com.example.entity.Driver;
import com.example.entity.DriverCar;
import com.example.entity.OnlineStatus;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

/**
 * Created by vinodjagwani on 7/15/17.
 */

public class DriverCarRepositoryTest extends AbstractRepositoryTest
{

    @Autowired
    private DriverCarRepository driverCarRepository;


    @Test
    public void testFindByDriverIdAndCarId()
    {
        DriverCar driverCar = getDriverCar();
        driverCarRepository.save(driverCar);
        DriverCar savedDriverCar = driverCarRepository.findByDriverIdAndCarId(1L, 1L);
        Assert.assertNotNull(savedDriverCar);
    }

    @Test
    public void testFindByDriverSeatCount()
    {
        DriverCar driverCar = getDriverCar();
        driverCarRepository.save(driverCar);
        CarData carData = CarData.builder().seatCount(3).build();
        List<Object[]> drivers = driverCarRepository.findDriverByCarAttributes(carData);
        drivers.forEach(obj ->
        {
            Driver driver = (Driver) obj[0];
            Car car = (Car) obj[1];
            Assert.assertEquals(OnlineStatus.OFFLINE, driver.getOnlineStatus());
            Assert.assertEquals(Integer.valueOf(3), car.getSeatCount());
        });
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testAlreadyExistCarWithDriver()
    {
        DriverCar driverCar1 = getDriverCar();
        DriverCar driverCar2 = getDriverCar();
        driverCarRepository.save(driverCar1);
        DriverCar savedDriverCar = driverCarRepository.findByDriverIdAndCarId(1L, 1L);
        driverCarRepository.save(driverCar2);
        Assert.assertNotNull(savedDriverCar);
    }

    private DriverCar getDriverCar()
    {
        DriverCar driverCar = new DriverCar();
        driverCar.setDriverId(1L);
        driverCar.setCarId(1L);
        return driverCar;
    }

}
