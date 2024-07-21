package com.template.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.template.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUserName(String userName);
}