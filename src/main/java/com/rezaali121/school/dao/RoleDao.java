package com.rezaali121.school.dao;

import com.rezaali121.school.model.Role;
import com.rezaali121.school.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

}
