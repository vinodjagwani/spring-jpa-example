package com.example.service;

import com.example.AbstractTest;
import com.example.entity.Driver;
import com.example.repository.DriverRepository;
import com.example.service.impl.DefaultUserService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
/**
 * Created by vinodjagwani on 7/15/17.
 */
public class UserServiceTest extends AbstractTest
{

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DefaultUserService userService;


    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(DefaultUserService.class);
    }


    @Test
    public void testLoadUserByUsername()
    {
        Driver driver = getDriver();
        when(driverRepository.findByUsername(any(String.class))).thenReturn(driver);
        userService.loadUserByUsername(any(String.class));
        verify(driverRepository, times(1)).findByUsername(any(String.class));
    }
}
