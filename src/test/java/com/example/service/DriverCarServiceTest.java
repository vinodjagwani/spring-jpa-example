package com.example.service;

import com.example.AbstractTest;
import com.example.dto.CarData;
import com.example.entity.DriverCar;
import com.example.repository.DriverCarRepository;
import com.example.service.impl.DefaultDriverCarService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinodjagwani on 7/15/17.
 */
public class DriverCarServiceTest extends AbstractTest
{

    @Mock
    private DriverCarRepository driverCarRepository;

    @InjectMocks
    private DefaultDriverCarService driverCarService;


    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(DefaultDriverCarService.class);
    }


    @Test
    public void testDelete()
    {
        DriverCar driverCar = getDriverCar();
        doNothing().when(driverCarRepository).delete(any(DriverCar.class));
        driverCarService.delete(driverCar);
        verify(driverCarRepository, times(1)).delete(any(DriverCar.class));
    }


    @Test
    public void testSave()
    {
        DriverCar driverCar = getDriverCar();
        when(driverCarRepository.save(any(DriverCar.class))).thenReturn(driverCar);
        driverCarService.save(driverCar);
        verify(driverCarRepository, times(1)).save(any(DriverCar.class));
    }


    @Test
    public void testFindByDriverIdAndCarId()
    {
        DriverCar driverCar = getDriverCar();
        when(driverCarRepository.findByDriverIdAndCarId(any(Long.class),any(Long.class))).thenReturn(driverCar);
        driverCarService.findByDriverIdAndCarId(1L, 1L);
        verify(driverCarRepository, times(1)).findByDriverIdAndCarId(any(Long.class),any(Long.class));
    }

    @Test
    public void  testFindDriverByCarAttributes()
    {
        CarData carData = getCarData();
        List<Object[]> objects = new ArrayList<>();
        Object[] object = new Object[2];
        object[0] = getDriver();
        object[1] = getCar();
        objects.add(object);
        when(driverCarRepository.findDriverByCarAttributes(any(CarData.class))).thenReturn(objects);
        driverCarService.findDriverByCarAttributes(carData);
        verify(driverCarRepository, times(1)).findDriverByCarAttributes(any(CarData.class));
    }
}
