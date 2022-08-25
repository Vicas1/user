package com.zek.doorstep.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zek.doorstep.entity.Users;

/**
 * @author EK
 *
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	
	public Boolean existsByEmail (String userEmail);
	Optional<Users> findByEmail(String emailAddress);

}
