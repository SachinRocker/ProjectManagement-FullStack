package com.simpleUI.projectmanagementtool.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class FieldsValidationService {

	public ResponseEntity<?> getFiledErrorResponse(BindingResult results) {
		if (results.hasErrors()) {

			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : results.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		return null;
	}

}
