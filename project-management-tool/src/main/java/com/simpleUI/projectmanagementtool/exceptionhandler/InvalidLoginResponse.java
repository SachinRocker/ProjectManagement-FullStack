package com.simpleUI.projectmanagementtool.exceptionhandler;

public class InvalidLoginResponse {
	
	private String userName;
	private String password;
	
	public InvalidLoginResponse() {
		this.userName="Invalid Username";
		this.password = "Invalid Password";
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
