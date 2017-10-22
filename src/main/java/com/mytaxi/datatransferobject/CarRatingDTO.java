package com.mytaxi.datatransferobject;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

public class CarRatingDTO
{
    @DecimalMin(value = "0", message = "rating must be between 0 and 100!")
    @DecimalMax(value = "100", message = "rating must be between 0 and 100!")
    private Integer rating;

    public Integer getRating()
    {
        return rating;
    }

    public void setRating(Integer rating)
    {
        this.rating = rating;
    }
}
