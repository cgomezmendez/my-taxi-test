package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.DriverDO;

import java.util.ArrayList;
import java.util.List;

public class DriverCriteriaSelectedCar implements DriverCriteria
{
    @Override
    public List<DriverDO> meetCriteria(List<DriverDO> drivers)
    {
        List<DriverDO> driversWithSelectedCars = new ArrayList<>();
        for (DriverDO driver: drivers)
        {
            if (driver.getSelectedCar() != null)
            {
                driversWithSelectedCars.add(driver);
            }
        }
        return driversWithSelectedCars;
    }
}
