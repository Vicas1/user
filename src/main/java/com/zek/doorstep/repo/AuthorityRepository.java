/**
 * 
 */
package com.zek.doorstep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zek.doorstep.entity.Authority;

/**
 * @author EK
 *
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer>{

}
