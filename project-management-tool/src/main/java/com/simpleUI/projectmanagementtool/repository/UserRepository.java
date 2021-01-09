package com.simpleUI.projectmanagementtool.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simpleUI.projectmanagementtool.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	public User findByUsername(String username);
	public Optional<User> findById(Long id);
	

	}
