package com.rezaali121.school.dao;

import com.rezaali121.school.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleDao extends JpaRepository<Module, Integer> {

}
