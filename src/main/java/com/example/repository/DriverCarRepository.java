package com.example.repository;

import com.example.dto.CarData;
import com.example.entity.DriverCar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by vinodjagwani on 7/15/17.
 */
public interface DriverCarRepository extends CrudRepository<DriverCar, Long>
{
    DriverCar findByDriverIdAndCarId(final Long driverId, final Long carId);

    @Query("SELECT D, C FROM Car C, Driver D, DriverCar B " +
           "WHERE B.carId = C.id AND D.id = B.driverId " +
           "AND (C.seatCount = :#{#carData.seatCount} OR C.convertible = :#{#carData.convertible} " +
           "OR C.engineType = :#{#carData.engineType})"
    )
    List<Object[]> findDriverByCarAttributes(@Param("carData") final CarData carData);

}
