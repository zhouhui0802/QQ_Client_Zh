package com.zh.model;

import java.io.*;
import java.net.*;

import com.zh.client.tools.ClientConServerThread;
import com.zh.client.tools.ManageClientConServerThread;
import com.zh.common.Message;
import com.zh.common.User;

public class QqClientConServer {
	
	public Socket s;
	
	public boolean SendLoginInfoServer(Object o)
	{
		boolean b=false;
		//正式进入判断函数，跟服务器连接
		
		try{
			s=new Socket("127.0.0.1",9999);
			
			//连接服务器分两步，第一步 读取界面的内容
			System.out.println("第一步，将客户端的用户和密码进行读取");
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			//第二步读取服务器处理之后的内容
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			Message ms=(Message)ois.readObject();
			System.out.println("第五步: 读取服务器端写入的登录信号，判断是否可以登录");
			//对用户进行验证
			if(ms.getMesType().equals("1"))
			{
				//创建一个该QQ号和服务器保持通讯连接的线程
				System.out.println("第六步: 创建一个该QQ号和服务器保持通信的线程");
				ClientConServerThread ccst=new ClientConServerThread(s);		
				//启动该通讯线程
				ccst.start();			
				ManageClientConServerThread.addClientConServerThread(((User)o).getUserId(), ccst);
				b=true;
			}else
			{
				s.close();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			
		}
		
		return b;
	}
}
