package com.example.repository;

import com.example.entity.Driver;
import com.example.entity.OnlineStatus;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import java.util.List;

/**
 * Created by vinodjagwani on 7/15/17.
 */

public class DriverRepositoryTest  extends AbstractRepositoryTest
{

    private static final String USER_NAME = "driver02";


    @Autowired
    private DriverRepository driverRepository;


    @Test
    public void testDriverById()
    {
        Driver driver = driverRepository.findOne(1L);
        Assert.assertNotNull(driver);
    }


    @Test
    public void testDriverByOnlineStatus()
    {
        List<Driver> onlineDrivers = driverRepository.findByOnlineStatus(OnlineStatus.ONLINE);
        Assert.assertThat(onlineDrivers, hasSize(4));
    }


    @Test
    public void testDriverByOfflineStatus()
    {
        List<Driver> offlineDrivers = driverRepository.findByOnlineStatus(OnlineStatus.OFFLINE);
        Assert.assertThat(offlineDrivers, hasSize(4));
    }


    @Test
    public void testDriverByUsername()
    {
        Driver driver = driverRepository.findByUsername(USER_NAME);
        Assert.assertNotNull(driver);
    }
}
