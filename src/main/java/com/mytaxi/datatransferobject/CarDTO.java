package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mytaxi.domainvalue.EngineType;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO
{
    @JsonIgnore
    private Long id;

    @NotNull(message = "LicensePlate can not be null!")
    private String licensePlate;

    @NotNull(message = "SeatCount can not be null!")
    private Integer seatCount;

    private boolean convertible;

    @NotNull(message = "EngineType can not be null!")
    private EngineType engineType;

    @DecimalMin(value = "0", message = "rating must be between 0 and 100!")
    @DecimalMax(value = "100", message = "rating must be between 0 and 100!")
    private Integer rating;

    @NotNull(message = "Manufacturer can not be null!")
    private ManufacturerDTO manufacturer;

    public CarDTO()
    {
    }

    public CarDTO(Long id, String licensePlate, Integer seatCount, boolean convertible, EngineType engineType, int rating, ManufacturerDTO manufacturer)
    {
        this.id = id;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.engineType = engineType;
        this.rating = rating;
        this.manufacturer = manufacturer;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLicensePlate()
    {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }

    public Integer getSeatCount()
    {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount)
    {
        this.seatCount = seatCount;
    }

    public boolean isConvertible()
    {
        return convertible;
    }

    public void setConvertible(boolean convertible)
    {
        this.convertible = convertible;
    }

    public EngineType getEngineType()
    {
        return engineType;
    }

    public void setEngineType(EngineType engineType)
    {
        this.engineType = engineType;
    }

    public Integer getRating()
    {
        return rating;
    }

    public void setRating(Integer rating)
    {
        this.rating = rating;
    }

    public ManufacturerDTO getManufacturer()
    {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerDTO manufacturer)
    {
        this.manufacturer = manufacturer;
    }

    public static class CarDTOBuilder
    {
        private Long id;
        private String licensePlate;
        private Integer seatCount;
        private boolean convertible;
        private EngineType engineType;
        private int rating;
        private ManufacturerDTO manufacturer;

        public CarDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }

        public CarDTOBuilder setLicensePlate(String licensePlate)
        {
            this.licensePlate = licensePlate;
            return this;
        }

        public CarDTOBuilder setSeatCount(Integer seatCount)
        {
            this.seatCount = seatCount;
            return this;
        }

        public CarDTOBuilder setConvertible(boolean convertible)
        {
            this.convertible = convertible;
            return this;
        }

        public CarDTOBuilder setEngineType(EngineType engineType)
        {
            this.engineType = engineType;
            return this;
        }

        public CarDTOBuilder setRating(int rating)
        {
            this.rating = rating;
            return this;
        }

        public CarDTOBuilder setManufacturer(ManufacturerDTO manufacturer)
        {
            this.manufacturer = manufacturer;
            return this;
        }

        public CarDTO createCarDTO() {
            return new CarDTO(id, licensePlate, seatCount, convertible,
                    engineType, rating, manufacturer);
        }
    }
}
