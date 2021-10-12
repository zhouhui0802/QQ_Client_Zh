package com.zh.client.tools;

import java.util.HashMap;

import com.zh.client.view.QqChat;

public class ManageQqChat {

	private static HashMap hm=new HashMap<String,QqChat>();
	
	public static void addQqChat(String loginIdAndFriendId,QqChat qqChat)
	{
		hm.put(loginIdAndFriendId, qqChat);
	}
	
	public static QqChat getQqChat(String loginIdAndFriend)
	{
		return (QqChat)hm.get(loginIdAndFriend);
	}
}
