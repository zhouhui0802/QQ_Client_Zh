package com.zh.client.tools;

import java.util.HashMap;

import com.zh.client.view.MultiQqChat;
public class ManageMultiQqChat {
	
    private static HashMap<String, MultiQqChat> hm = new HashMap<String, MultiQqChat>();

    // ����һ���������
    public static void addMultiQqChat(String loginIdAndMultiChatId, MultiQqChat multiChat) {
        hm.put(loginIdAndMultiChatId, multiChat);
    }

    // ��ȡһ���������
    public static MultiQqChat getMultiQqChat(String loginIdAndMultiChatId) {
        return (MultiQqChat) hm.get(loginIdAndMultiChatId);
    }
    //ɾ��һ��Ⱥ�Ľ���
    public static void removeMultiQqChat(String loginIdAndMultiChatId){
        hm.remove(loginIdAndMultiChatId);
    }
    
}
