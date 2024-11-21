package com.template.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.template.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value = "SELECT password FROM users WHERE user_name = :userName", nativeQuery = true)
    String findPasswordByUserName(@Param("userName") String userName);
	
	Optional<User> findByUserName(String userName);
	
	Optional<User> findByPassword(String password);
	
	Optional<User> findByUserNameAndPassword(String userName, String password );
 }