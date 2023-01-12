package com.rezaali121.school.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.rezaali121.school.dao.ModuleDao;
import com.rezaali121.school.model.Module;
import com.rezaali121.school.view.ModuleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ModuleController {

    @Autowired
    private ModuleDao moduleDao;

    @GetMapping("/modules")
    @JsonView(ModuleView.class)
    public List<Module> getAllModule(){
        return moduleDao.findAll();
    }

    @GetMapping("/module/{id}")
    @JsonView(ModuleView.class)
    public ResponseEntity<Module> getModuleById(@PathVariable  int id){

        Optional<Module> module = moduleDao.findById(id);

        if(module.isPresent()){
            return new ResponseEntity<>(module.get() , HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/module")
    public ResponseEntity<Module> saveModule(@RequestBody Module module){


        if(module == null || module.getName().equals("") ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(module.getId() != null){
            Optional<Module> moduleDatabase = moduleDao.findById(module.getId());
            if(moduleDatabase.isEmpty())
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            // the module exists and we just update the existed module
            moduleDao.save(module);

            return new ResponseEntity<>(HttpStatus.OK);
        }



        // module does not exist creating a new module
        moduleDao.save(module);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @DeleteMapping("/admin/module/{id}")
    public ResponseEntity<Module> deleteModule(@PathVariable int id){
        Optional<Module> moduleDatabase = moduleDao.findById(id);
        if(moduleDatabase.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        moduleDao.deleteById(id);
        return new ResponseEntity<>(moduleDatabase.get(),HttpStatus.OK);
    }
}
