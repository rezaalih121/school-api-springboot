package com.rezaali121.school.dao;

import com.rezaali121.school.model.Administrator;
import com.rezaali121.school.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorDao extends JpaRepository<Administrator, Integer> {

}
