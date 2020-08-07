package com.example.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.main.model.User;
import com.example.main.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public User getUserById(Long id) {
		return userRepo.findById(id).orElse(null);
	}

}
