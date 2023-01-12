package com.rezaali121.school.controller;

import com.rezaali121.school.dao.RoleDao;
import com.rezaali121.school.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RoleController {

    @Autowired
    private RoleDao roleDao;

    @GetMapping("/roles")
    public List<Role> getAllRole(){
        return roleDao.findAll();
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable  int id){

        Optional<Role> role = roleDao.findById(id);

        if(role.isPresent()){
            return new ResponseEntity<>(role.get() , HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/role")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){


        if(role == null || role.getName().equals("") ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(role.getId() != null){
            Optional<Role> roleDatabase = roleDao.findById(role.getId());
            if(roleDatabase.isEmpty())
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            // the role exists and we just update the existed role
            roleDao.save(role);

            return new ResponseEntity<>(HttpStatus.OK);
        }



        // role does not exist creating a new role
        roleDao.save(role);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @DeleteMapping("/admin/role/{id}")
    public ResponseEntity<Role> deleteRole(@PathVariable int id){
        Optional<Role> roleDatabase = roleDao.findById(id);
        if(roleDatabase.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        roleDao.deleteById(id);
        return new ResponseEntity<>(roleDatabase.get(),HttpStatus.OK);
    }
}
