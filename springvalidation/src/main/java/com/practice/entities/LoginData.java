package com.practice.entities;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginData {

	@Valid
	@NotBlank(message = "username can not be blank !!")
	@Size(min=3, max=12, message="min 3 and max 12 chars allowed !!")
	private String userName;
	
	@Pattern(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message="Invalid email !!")
	private String email;
	
	@AssertTrue(message="must agree terms and conditions")
	private boolean agreed;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isAgreed() {
		return agreed;
	}
	public void setAgreed(boolean agreed) {
		this.agreed = agreed;
	}
	@Override
	public String toString() {
		return "LoginData [userName=" + userName + ", email=" + email + "]";
	}
	
	
}
