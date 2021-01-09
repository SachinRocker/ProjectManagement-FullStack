package com.simpleUI.projectmanagementtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.simpleUI.projectmanagementtool.domain.User;
import com.simpleUI.projectmanagementtool.exceptionhandler.UsernameCustomException;
import com.simpleUI.projectmanagementtool.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public User save(User newUser) {

		try {

			newUser.setPassword(encoder.encode(newUser.getPassword()));
			newUser.setUsername(newUser.getUsername());
			newUser.setConfirmPassword("");

			// password and confirm password has to match
			// we don't persist or store the confirm password

			return userRepository.save(newUser);

		} catch (Exception e) {
			// TODO: handle exception
			throw new UsernameCustomException("Username '" + newUser.getUsername() + "' already exists!");
		}

		

	}

}
