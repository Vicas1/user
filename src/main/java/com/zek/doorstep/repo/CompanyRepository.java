package com.zek.doorstep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zek.doorstep.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{

}
