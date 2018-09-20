package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.entity.GeoCoordinate;
import javax.validation.constraints.NotNull;

/**
 * This class is responsible for Driver data.
 * <p/>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverData
{
    @JsonIgnore
    private Long id;


    public CarData getCarData()
    {
        return carData;
    }


    public void setCarData(CarData carData)
    {
        this.carData = carData;
    }


    @JsonProperty("car")
    private CarData carData;

    @NotNull(message = "Username can not be null!")
    private String username;

    @NotNull(message = "Password can not be null!")
    private String password;

    private GeoCoordinate coordinate;


    private DriverData()
    {
    }


    private DriverData(Long id, String username, String password, GeoCoordinate coordinate, CarData carData)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.coordinate = coordinate;
        this.carData = carData;
    }


    public static DriverDTOBuilder newBuilder()
    {
        return new DriverDTOBuilder();
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }


    public String getUsername()
    {
        return username;
    }


    public String getPassword()
    {
        return password;
    }


    public GeoCoordinate getCoordinate()
    {
        return coordinate;
    }

    public static class DriverDTOBuilder
    {
        private Long id;
        private String username;
        private String password;
        private GeoCoordinate coordinate;
        private CarData carData;


        public DriverDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public DriverDTOBuilder setUsername(String username)
        {
            this.username = username;
            return this;
        }


        public DriverDTOBuilder setPassword(String password)
        {
            this.password = password;
            return this;
        }


        public DriverDTOBuilder setCoordinate(GeoCoordinate coordinate)
        {
            this.coordinate = coordinate;
            return this;
        }

        public DriverDTOBuilder setCarDto(CarData carData)
        {
            this.carData = carData;
            return this;
        }


        public DriverData createDriverDTO()
        {
            return new DriverData(id, username, password, coordinate, carData);
        }

    }
}
