package com.zh.common;

public class User implements java.io.Serializable{
	
	//三种登录方式
	private String userId;
	private String passwd;
	private String phone;
	private String phonePasswd;
	private String email;
	private String emailPasswd;
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserId() {
		return userId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhonePasswd() {
		return phonePasswd;
	}
	public void setPhonePasswd(String phonePasswd) {
		this.phonePasswd = phonePasswd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailPasswd() {
		return emailPasswd;
	}
	public void setEmailPasswd(String emailPasswd) {
		this.emailPasswd = emailPasswd;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	
}
