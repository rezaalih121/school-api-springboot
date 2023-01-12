package com.rezaali121.school.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.rezaali121.school.dao.UserDao;
import com.rezaali121.school.model.User;
import com.rezaali121.school.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/users")
    @JsonView(UserView.class)
    public List<User> getAllUser(){
        return userDao.findAll();
    }

    @GetMapping("/user/{id}")
    @JsonView(UserView.class)
    public ResponseEntity<User> getUserById(@PathVariable  int id){

        Optional<User> user = userDao.findById(id);

        if(user.isPresent()){
            return new ResponseEntity<>(user.get() , HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/user")
    public ResponseEntity<User> saveUser(@RequestBody User user){


        if(user == null || user.getEmail().equals("") || user.getPassword().equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(user.getId() != null){
            Optional<User> userDatabase = userDao.findById(user.getId());
            if(userDatabase.isEmpty())
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            // the user exists and we just update the existed user
            userDao.save(user);

            return new ResponseEntity<>(HttpStatus.OK);
        }



        // user does not exist creating a new user
        userDao.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @DeleteMapping("/admin/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
        Optional<User> userDatabase = userDao.findById(id);
        if(userDatabase.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        userDao.deleteById(id);
        return new ResponseEntity<>(userDatabase.get(),HttpStatus.OK);
    }
}
