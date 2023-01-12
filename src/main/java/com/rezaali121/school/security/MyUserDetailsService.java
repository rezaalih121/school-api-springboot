package com.rezaali121.school.security;

import com.rezaali121.school.dao.UserDao;
import com.rezaali121.school.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        Optional<User> user = userDao.findByEmail(username);

        if(user.isEmpty())
            throw new UsernameNotFoundException("The email given is not exists");

        return new MyUserDetails(user.get());

    }
}
