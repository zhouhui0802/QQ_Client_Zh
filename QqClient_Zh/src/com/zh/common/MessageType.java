package com.zh.common;

public interface MessageType {
	
	//表明登录成功
	String message_succeed="1";
	
	//表明登录失败
	String message_login_fail="2";
	
	//普通信息包
	String message_comm_mes="3";
	
	//要求在线好友
	String message_get_onLineFriend="4";
	
	//返回在线好友
	String message_ret_onLineFriend="5";
	
	//表示离开
	String message_exit="6";
	
	//表示群聊信息
	String message_multi="7";
	
	//显示文件的传输
	String message_file="8";
}
