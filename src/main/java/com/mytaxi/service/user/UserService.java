package com.mytaxi.service.user;

import com.mytaxi.domainobject.UserDO;
import com.mytaxi.exception.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
    public UserDO find(String username) throws EntityNotFoundException;
}
