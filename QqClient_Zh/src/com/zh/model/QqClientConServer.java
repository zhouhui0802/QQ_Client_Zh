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
		//��ʽ�����жϺ�����������������
		
		try{
			s=new Socket("127.0.0.1",9999);
			
			//���ӷ���������������һ�� ��ȡ���������
			System.out.println("��һ�������ͻ��˵��û���������ж�ȡ");
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			//�ڶ�����ȡ����������֮�������
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			Message ms=(Message)ois.readObject();
			System.out.println("���岽: ��ȡ��������д��ĵ�¼�źţ��ж��Ƿ���Ե�¼");
			//���û�������֤
			if(ms.getMesType().equals("1"))
			{
				//����һ����QQ�źͷ���������ͨѶ���ӵ��߳�
				System.out.println("������: ����һ����QQ�źͷ���������ͨ�ŵ��߳�");
				ClientConServerThread ccst=new ClientConServerThread(s);		
				//������ͨѶ�߳�
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
