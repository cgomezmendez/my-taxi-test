package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;

public class ManufacturerDTO
{
    @JsonIgnore
    private Long id;

    @NotNull(message = "Name can not be null!")
    private String name;

    public ManufacturerDTO()
    {
    }

    public ManufacturerDTO(Long id, String name)
    {
        this.id = id;
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

    public static class ManufacturerDTOBuilder {
        private Long id;
        private String name;

        public ManufacturerDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }

        public ManufacturerDTOBuilder setName(String name)
        {
            this.name = name;
            return this;
        }

        public ManufacturerDTO createManufacturerDTO() {
            return new ManufacturerDTO(id, name);
        }
    }
}
