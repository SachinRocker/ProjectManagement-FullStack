package com.simpleUI.projectmanagementtool.exceptionhandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ProjectExceptionHandler {
	
	
	@ExceptionHandler
	public ResponseEntity<Object> handlerProjectIdException(ProjectIDException exc,WebRequest request){
		
		ProjectIdErrorResponse errorResponse = new ProjectIdErrorResponse(exc.getMessage());
		
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler
	public ResponseEntity<Object> handleBacklogException(BacklogCustomException exc,WebRequest request){
		
		BacklogErrorResponse errorResponse = new BacklogErrorResponse(exc.getMessage());
		
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
		
	}
	
	
	

}
