package com.zek.doorstep.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zek.doorstep.entity.Users;
import com.zek.doorstep.pojos.ResponseModel;
import com.zek.doorstep.pojos.dtos.UsersDtos;
import com.zek.doorstep.repo.UserRepository;
import com.zek.doorstep.util.CommonConstant;


/**
 * @author EK
 *
 */
@Service
@Transactional
public class UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    private ModelMapper modelMapper;

	
	public ResponseModel getAllUsers() {
		ResponseModel response = new ResponseModel();		
		String status = "";
		String message = "";		
		try {			
			
			List<Users> users = userRepository.findAll();
			response.setPayload(users/*
								 * users.stream() .map(user -> modelMapper.map(user, UsersDtos.class))
								 * .collect(Collectors.toList())
								 */);
			
			status = CommonConstant.SUCCESS;
			message = CommonConstant.SUCCESS;
			}
		catch(Exception e) {
			e.printStackTrace();
			status = CommonConstant.FAILED;
			message = e.getMessage();
		}
		response.setStatus(status);
		response.setMessage(message);
		return response;
	}


	public ResponseModel getUser(Long id) {
		ResponseModel response = new ResponseModel();		
		String status = "";
		String message = "";		
		try {			
			
			Users user = userRepository.findById(id)
			.orElseThrow(() -> new Exception("Not Exist"));
			
			response.setPayload(user);
			
			status = CommonConstant.SUCCESS;
			message = CommonConstant.SUCCESS;
			}
		catch(Exception e) {
			e.printStackTrace();
			status = CommonConstant.FAILED;
			message = e.getMessage();
		}
		response.setStatus(status);
		response.setMessage(message);
		return response;
	}
	
	
	public ResponseModel checkMobileExist(String userEmail) {	
		ResponseModel response = new ResponseModel();	
		 response.setPayload(userRepository.existsByEmail(userEmail));
		response.setStatus(CommonConstant.SUCCESS);
		response.setMessage(CommonConstant.SUCCESS);
		return response;
	}
	
}
