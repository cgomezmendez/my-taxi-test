package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.DriverDO;

import java.util.List;

public interface Criteria<T>
{
    public List<T> meetCriteria(List<T> list);
}
