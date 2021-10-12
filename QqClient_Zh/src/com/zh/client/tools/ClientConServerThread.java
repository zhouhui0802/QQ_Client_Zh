package com.zh.client.tools;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

import com.zh.client.view.MultiQqChat;
import com.zh.client.view.QqChat;
import com.zh.client.view.QqFriendList;
import com.zh.client.view.ReceiveFile;
import com.zh.common.Message;
import com.zh.common.MessageType;

public class ClientConServerThread extends Thread{
	
	private Socket s;
	
	//���캯��
	public ClientConServerThread(Socket s)
	{
		this.s=s;
	}
	
	public void run()
	{
		while(true)
		{
			try{
			    
				System.out.println("��ʮһ���� �̲߳��϶�ȡ�ӷ���������������");
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				
				if(m.getMesType().equals(MessageType.message_succeed))
				{
					System.out.println("ֻ�����˵�¼����");
					
				}else if(m.getMesType().equals(MessageType.message_ret_onLineFriend))
				{
					
					String con=m.getCon();
					System.out.println("con="+con);
					String friends[]=con.split(" ");
					String getter=m.getGetter();
					
					//�޸ĺ����б�
					QqFriendList qqFriendList=ManageQqFriendList.getQqFriendList(getter);
					
					//�������ߺ���
					if(qqFriendList!=null)
					{
						qqFriendList.updateFriend(m);
					}
					
				}else if(m.getMesType().equals(MessageType.message_comm_mes))
				{
					QqChat qqChat=ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					qqChat.showMessage(m);
					
				}else if(m.getMesType().equals(MessageType.message_file))
				{
					System.out.println("����ͻ��˵��ļ�����");
					QqChat qqChat=ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					
					if(qqChat!=null)
					{
						int n=JOptionPane.showConfirmDialog(null, "�Ƿ����?", "�ļ�����",JOptionPane.YES_NO_OPTION);
						if(n==0)
						{
							ReceiveFile rf=new ReceiveFile(m.getGetter(),m.getSender());
							rf.file=m.getFile();
						}
					}
					
					File file=new File(m.getFile().getName());   //�ļ�����
					System.out.println(m.getFile().getName());
					FileInputStream fis=new FileInputStream(m.getFile());   //�ļ�����·��
					
					
					FileOutputStream fos=new FileOutputStream(file);
					try{
						byte[] buf=new byte[1024];
						
						//��¼ʵ�ʶ�ȡ�����ֽ�
						int n=0;
						
						//ѭ����ȡ
						while((n=fis.read(buf))!=-1)
						{
							//�����ָ���ļ�
							fos.write(buf);
						}
					}catch(Exception e)
					{
						e.printStackTrace();
					}
	
					qqChat.showMessage(m);
					
				}else if(m.getMesType().equals(MessageType.message_multi))
				{
					MultiQqChat mqc=ManageMultiQqChat.getMultiQqChat(m.getGetter()+" "+m.getMultiChat());
					if(mqc==null)
					{
						mqc=new MultiQqChat(m.getGetter(),m.getMultiChat());
						ManageMultiQqChat.addMultiQqChat(m.getGetter()+" "+m.getMultiChat(), mqc);
					}
					mqc.showMessages(m);
				}
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}
	
}
