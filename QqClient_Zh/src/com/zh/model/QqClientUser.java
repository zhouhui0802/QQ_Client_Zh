package com.zh.model;

import com.zh.common.User;

public class QqClientUser {
	
	public boolean checkUser(User u)
	{
		return new QqClientConServer().SendLoginInfoServer(u);
	}
}
