package com.example.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.main.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	//User getUserById(Long userId);
	//User findByName(String userId);

	
}
