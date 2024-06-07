package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(User user2);

	User findByUsername(String username);
}
