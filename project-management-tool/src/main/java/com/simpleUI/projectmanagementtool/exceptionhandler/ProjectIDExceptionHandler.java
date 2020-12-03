package com.simpleUI.projectmanagementtool.exceptionhandler;

import java.lang.reflect.Executable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class ProjectIDExceptionHandler {
	
	
	@ExceptionHandler
	public ResponseEntity<Object> handlerProjectIdException(ProjectIDException exc,WebRequest request){
		
		return new ResponseEntity<Object>(exc.getMessage(), HttpStatus.BAD_REQUEST);
		
	}
	

}
