package com.mytaxi.domainobject;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(
        name = "manufacturer",
        uniqueConstraints = @UniqueConstraint(name = "uc_name",
                columnNames = {"name"})
)
public class ManufacturerDO
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "manufacturer")
    private List<CarDO> cars;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    public ManufacturerDO()
    {
    }

    public ManufacturerDO(String name)
    {
        this.name = name;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<CarDO> getCars()
    {
        return cars;
    }

    public void setCars(List<CarDO> cars)
    {
        this.cars = cars;
    }
}
