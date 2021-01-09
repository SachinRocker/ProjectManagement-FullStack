package com.simpleUI.projectmanagementtool.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simpleUI.projectmanagementtool.domain.User;
import com.simpleUI.projectmanagementtool.payload.JWTLoginSuccessResponse;
import com.simpleUI.projectmanagementtool.payload.LoginRequest;
import com.simpleUI.projectmanagementtool.security.JWTTokenProvider;
import com.simpleUI.projectmanagementtool.security.SecurityConstants;
import com.simpleUI.projectmanagementtool.services.FieldsValidationService;
import com.simpleUI.projectmanagementtool.services.UserService;
import com.simpleUI.projectmanagementtool.validator.UserValidator;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private FieldsValidationService validationService;
	@Autowired
	private UserValidator validator;

	@Autowired
	JWTTokenProvider jwtTokenProvider;
	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult bindingResult) {

		validator.validate(user, bindingResult);

		ResponseEntity<?> errorResponse = validationService.getFiledErrorResponse(bindingResult);

		if (errorResponse != null)
			return errorResponse;
		User savedUser = userService.save(user);

		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);

	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
			BindingResult bindingResult) {

		ResponseEntity<?> errorResponse = validationService.getFiledErrorResponse(bindingResult);

		if (errorResponse != null)
			return errorResponse;
		
		Authentication authentication = authenticationManager.authenticate(
														new UsernamePasswordAuthenticationToken
														(loginRequest.getUsername(), 
														loginRequest.getPassword()
														)
													);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

		return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));

	}

}
