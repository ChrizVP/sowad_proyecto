package com.proyecto.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.app.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsername(String username);

		
}
