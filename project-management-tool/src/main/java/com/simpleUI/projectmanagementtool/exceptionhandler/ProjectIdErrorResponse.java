package com.simpleUI.projectmanagementtool.exceptionhandler;

public class ProjectIdErrorResponse {
	
	private String projectIdentifier;

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void getProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	public ProjectIdErrorResponse(String projectIdentifier) {
		
		this.projectIdentifier = projectIdentifier;
	}

	public ProjectIdErrorResponse() {
		
	}
	
	
	

}
