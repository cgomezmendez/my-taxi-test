package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.UserDO;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDO, Long>
{
    public UserDO findByUsername(String username);
}
