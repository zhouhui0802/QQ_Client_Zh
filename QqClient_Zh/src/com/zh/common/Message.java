package com.zh.common;

import java.io.File;

public class Message implements java.io.Serializable{
	
	//用户交流的方式
	private String mesType;
	
	//自身用户，算是发送者
	private String sender;
	
	//获取者，接收者
	private String getter;
	
	//传送的内容
	private String con;
	
	//发送的时间
	private String sendTime;
	
	//文件的传输
	private File file;
	
	//群聊信息
	private String MultiChat;
	
	public String getMultiChat() {
		return MultiChat;
	}

	public void setMultiChat(String multiChat) {
		MultiChat = multiChat;
	}

	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getMesType() {
		return mesType;
	}

	public void setMesType(String mesType) {
		this.mesType = mesType;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	
	
}
