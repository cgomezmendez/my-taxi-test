package com.mytaxi.service.user;

import com.mytaxi.dataaccessobject.UserRepository;
import com.mytaxi.domainobject.UserDO;
import com.mytaxi.exception.EntityNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class DefaultUserService implements UserService
{
    private final UserRepository userRepository;

    public DefaultUserService(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDO find(String username) throws EntityNotFoundException
    {
        UserDO userDO = userRepository.findByUsername(username);
        if (userDO == null) {
            throw new EntityNotFoundException("Could not find entity with username: " + username);
        }
        return userDO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserDO userDO = null;
        try
        {
            userDO = find(username);
        } catch (EntityNotFoundException e)
        {
            throw new UsernameNotFoundException("User not found with username: "+ username);
        }
        return new User(userDO.getUsername(), userDO.getPassword(),
                Collections.emptyList());
    }
}
