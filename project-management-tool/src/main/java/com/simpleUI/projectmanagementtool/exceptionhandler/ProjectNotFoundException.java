package com.simpleUI.projectmanagementtool.exceptionhandler;

public class ProjectNotFoundException extends RuntimeException {
	
	public ProjectNotFoundException(String message) {
		super(message);
	}

}
