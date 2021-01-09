package com.simpleUI.projectmanagementtool.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.simpleUI.projectmanagementtool.domain.User;

@Component
public class UserValidator implements Validator  {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user  = (User)target;
		
		if(user.getPassword() == null ||user.getPassword().length() < 6) {
			errors.rejectValue("password","Length","Password must be greater than 6 characters");
		}
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword","Match","Passwords must match");
		}
		
	}
	

}
