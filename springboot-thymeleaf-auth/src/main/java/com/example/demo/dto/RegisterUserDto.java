package com.example.demo.dto;

import javax.validation.constraints.NotBlank;

import com.example.demo.model.User;

public class RegisterUserDto extends User {
	
	@NotBlank(message="password field is required")
	private String confirmPassword;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "RegisterUserDto [confirmPassword=" + confirmPassword + ", getId()=" + getId() + ", getUsername()="
				+ getUsername() + ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	

}
