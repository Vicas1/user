package com.zek.doorstep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zek.doorstep.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
