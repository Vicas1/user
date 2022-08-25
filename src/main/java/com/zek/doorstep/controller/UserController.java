package com.zek.doorstep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zek.doorstep.pojos.ResponseModel;
import com.zek.doorstep.service.UserService;


/**
 * @author EK
 *
 */

@Validated
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@GetMapping
	public ResponseModel getAllUsers() {
		return userService.getAllUsers();		
	
	}
	
	@GetMapping("/{id}")
	public ResponseModel getUser(@PathVariable("id") Long id) {				
		return userService.getUser(id);		
	}
	
	@GetMapping("/{userEmail}")
	public ResponseModel checkMobileExist(@PathVariable("userEmail") String userEmail) {				
		return userService.checkMobileExist(userEmail);		
	}

}
