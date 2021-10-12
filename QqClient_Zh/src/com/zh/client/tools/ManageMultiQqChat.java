package com.zh.client.tools;

import java.util.HashMap;

import com.zh.client.view.MultiQqChat;
public class ManageMultiQqChat {
	
    private static HashMap<String, MultiQqChat> hm = new HashMap<String, MultiQqChat>();

    // 加入一个聊天界面
    public static void addMultiQqChat(String loginIdAndMultiChatId, MultiQqChat multiChat) {
        hm.put(loginIdAndMultiChatId, multiChat);
    }

    // 获取一个聊天界面
    public static MultiQqChat getMultiQqChat(String loginIdAndMultiChatId) {
        return (MultiQqChat) hm.get(loginIdAndMultiChatId);
    }
    //删除一个群聊界面
    public static void removeMultiQqChat(String loginIdAndMultiChatId){
        hm.remove(loginIdAndMultiChatId);
    }
    
}
