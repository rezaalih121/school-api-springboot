package com.rezaali121.school.security;

import com.rezaali121.school.dao.AdministratorDao;
import com.rezaali121.school.dao.UserDao;
import com.rezaali121.school.model.Administrator;
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

    @Autowired
    private AdministratorDao administratorDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        Optional<User> user = userDao.findByEmail(username);

        if(user.isEmpty())
            throw new UsernameNotFoundException("The email given is not exists");

        // if there is a record in administrator that means he is administrator
        // in this way we are protecting users and admins at same time because admin is not exist if it is not user
        // Notice that Administrator inherits User
        Optional<Administrator> admin = administratorDao.findById(user.get().getId());

        if(admin.isPresent()){
            return new MyUserDetails(admin.get());
        }

        return new MyUserDetails(user.get());

    }
}
