package com.simpleUI.projectmanagementtool.exceptionhandler;

public class BacklogErrorResponse {

	private String message;

	public BacklogErrorResponse(String message) {
		
		this.message = message;
	}

	public BacklogErrorResponse() {
		
	}
	
	public String getProjectNotFound() {
		return this.message;
		
	}


	
	
	

}
