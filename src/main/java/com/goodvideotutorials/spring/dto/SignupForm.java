package com.goodvideotutorials.spring.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Signup form DTO class
 * @author amitabh
 * other changes to ensure it is working
 */
public class SignupForm {
	
	@NotNull
	@Size(min=3,max=255)
	@Pattern(regexp="[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message="{emailPatternError}")
	private String email;
	
	@NotNull
	@Size(min=3,max=255,message="{nameSizeError}")
	private String name;
	
	@NotNull
	@Size(min=3,max=30,message="{emailSizeError}")
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString(){
		return "SignupForm [email=" + email + ", name=" + name + ", password="
				+ password + "]";
	}
}
