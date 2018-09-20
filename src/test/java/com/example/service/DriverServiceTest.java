package com.example.service;

import com.example.AbstractTest;
import com.example.entity.Driver;
import com.example.entity.OnlineStatus;
import com.example.exception.ConstraintsViolationException;
import com.example.exception.EntityNotFoundException;
import com.example.repository.DriverRepository;
import com.example.service.impl.DefaultDriverService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by vinodjagwani on 7/15/17.
 */
public class DriverServiceTest extends AbstractTest
{

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DefaultDriverService driverService;


    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(DefaultDriverService.class);
    }


    @Test
    public void testFindByDriverId()throws EntityNotFoundException
    {
        Driver driver = getDriver();
        when(driverRepository.findOne(any(Long.class))).thenReturn(driver);
        driverService.findByDriverId(any(Long.class));
        verify(driverRepository, times(1)).findOne(any(Long.class));
    }


    @Test
    public void testCreate() throws ConstraintsViolationException
    {
        Driver driver = getDriver();
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);
        driverService.create(any(Driver.class));
        verify(driverRepository, times(1)).save(any(Driver.class));
    }


    @Test
    public void testDelete() throws EntityNotFoundException
    {
        Driver driver = getDriver();
        when(driverRepository.findOne(any(Long.class))).thenReturn(driver);
        driverService.delete(any(Long.class));
        verify(driverRepository, times(1)).findOne(any(Long.class));
    }


    @Test
    public void testUpdateLocation() throws EntityNotFoundException
    {
        Driver driver = getDriver();
        when(driverRepository.findOne(any(Long.class))).thenReturn(driver);
        driverService.updateLocation(1L, 90.0, 90.0);
        verify(driverRepository, times(1)).findOne(any(Long.class));
    }


    @Test
    public void testFindByOnlineStatus()
    {
        List<Driver> drivers = Collections.singletonList(getDriver());
        when(driverRepository.findByOnlineStatus(any(OnlineStatus.class))).thenReturn(drivers);
        driverService.findByOnlineStatus(OnlineStatus.ONLINE);
        verify(driverRepository, times(1)).findByOnlineStatus(any(OnlineStatus.class));
    }


}
