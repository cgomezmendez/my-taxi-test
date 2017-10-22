package com.mytaxi.domainobject;

import com.mytaxi.datatransferobject.ManufacturerDTO;
import com.mytaxi.domainvalue.EngineType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "car",
        uniqueConstraints = @UniqueConstraint(name = "uc_licensePlate",
columnNames = {"licensePlate"}))
public class CarDO
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private Integer seatCount;

    @Column
    private Boolean convertible;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EngineType engineType;

    @Column
    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturerId")
    private ManufacturerDO manufacturer;

    @Column(nullable = false)
    private Boolean deleted = false;

    public CarDO()
    {
    }

    public CarDO(String licensePlate,
                 Integer seatCount, Boolean convertible, EngineType engineType,
                 Integer rating, ManufacturerDO manufacturer)
    {
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.engineType = engineType;
        this.rating = rating;
        this.manufacturer = manufacturer;
        this.deleted = deleted;
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

    public Boolean getConvertible()
    {
        return convertible;
    }

    public void setConvertible(Boolean convertible)
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

    public ManufacturerDO getManufacturer()
    {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerDO manufacturer)
    {
        this.manufacturer = manufacturer;
    }

    public Boolean getDeleted()
    {
        return deleted;
    }

    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }
}
