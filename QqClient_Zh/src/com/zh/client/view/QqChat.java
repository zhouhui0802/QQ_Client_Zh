package com.zh.client.view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.ObjectOutputStream;
import java.util.Date;

import javax.swing.*;

import com.zh.client.tools.ManageClientConServerThread;
import com.zh.client.util.FrameUtil;
import com.zh.common.Message;
import com.zh.common.MessageType;

public class QqChat extends JFrame implements ActionListener{

	JTextArea jta;
	JScrollPane jsp;
	
	JTextField jtf;
	JButton jb;
	JLabel label1;
	JButton jbFile;
	JButton jbSendFile;
	
	JPanel jp;
	String ownerId;
	String friendId;
	
	Box box1;
	Box box2;
	Box box3;
	
	JFileChooser jf;	
	File file ;//使用文件类获取选择器选择的文件

	
	//滑动面板

	public QqChat(String ownerId,String friend)
	{
		this.ownerId=ownerId;
		this.friendId=friend;
		
		jta=new JTextArea();
		jsp=new JScrollPane(jta);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		jtf=new JTextField(15);
		jb=new JButton("发送");
		
		label1=new JLabel("选择发送文件的路径");
		jbFile=new JButton("选择文件");
		
		jbSendFile=new JButton("发送文件");
		jb.addActionListener(this);
		jbFile.addActionListener(this);
		jbSendFile.addActionListener(this);
		
		jp=new JPanel();
		box1=Box.createHorizontalBox();
		box1.add(jtf);
		box1.add(jb);
		box1.add(jbSendFile);
		
		
//		box2=Box.createHorizontalBox();
//		box2.add(label1);
//		box2.add(jbFile);
//		box2.add(jbSendFile);
//		box3=Box.createVerticalBox();
//		box3.add(box1);
//		box3.add(box2);

		jp.add(box1);
		
		this.add(jsp,"Center");
		this.add(jp,"South");
		
		this.setTitle(ownerId+"正在和"+friend+"聊天");
		this.setIconImage((new ImageIcon("image/qq.gif")).getImage());
		this.setSize(380,200);
		this.setVisible(true);
		this.setResizable(false);
		FrameUtil.setFrameCenter(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb)
		{
			String info=ownerId+"对"+friendId+"说: "+jtf.getText();
			int countInfo=info.length();
			int Count1=countInfo/20;
			StringBuilder str=new StringBuilder(info);
			for(int i=0;i<Count1;i++)
			{
				str.insert(19*(i+1)+2*(i), "\r\n");
			}
			str.append("\r\n");
			info=str.toString();
				
			jta.append(info);
			Message m=new Message();
			m.setMesType(MessageType.message_comm_mes);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setSendTime(new java.util.Date().toString());
			
			//发送给服务器
			try{
				ObjectOutputStream oos=new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}else if(arg0.getSource()==jbSendFile)
		{
			SendFile sf=new SendFile(ownerId,friendId);
			//创建一个新的Message
//			Message m=new Message();
//			m.setSender(this.ownerId);
//			m.setGetter(this.friendId);
//			m.setMesType(MessageType.message_file);
//			m.setSendTime(new Date().toString());
//			m.setFile(file);		
//			String info=m.getSender()+"给"+m.getGetter()+"传输文件"+file.getName()+"\r\n";
//			this.jta.append(info);
//			
//            try{
//                ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(this.ownerId).getS().getOutputStream());
//                oos.writeObject(m);
//            } catch (Exception e){
//                e.printStackTrace();
//            }
		}
	}
	
	public void showMessage(Message m)
	{
		if(m.getMesType().equals(MessageType.message_comm_mes))
		{
			String info=m.getSender()+"对"+m.getGetter()+"说: "+m.getCon();
			int countInfo=info.length();
			int Count1=countInfo/20;
			StringBuilder str=new StringBuilder(info);
			for(int i=0;i<Count1;i++)
			{
				str.insert(i*(19+2+66),"                                                                   ");
				str.insert(19*(i+1)+2*(i)+66*(i+1), "\r\n");
			}
			str.insert(Count1*(19+2+66),"                                                                   ");
			str.append("\r\n");
			info=str.toString();
			
			this.jta.append(info);
		}else if(m.getMesType().equals(MessageType.message_file))
		{
			String info="文件: "+m.getFile().getName()+"传输完毕，已经接受\r\n";
			StringBuilder str=new StringBuilder(info);
			str.insert(0, "                                                                   ");
			str.append("\r\n");
			info=str.toString();
			System.out.println(info);
			this.jta.append(info);
		}

	}

}


//else if(arg0.getSource()==jbFile)
//{
//	jf = new JFileChooser();
//	jf.showOpenDialog(this);//显示打开的文件对话框
//	file =  jf.getSelectedFile();//使用文件类获取选择器选择的文件
//	String s = file.getAbsolutePath();//返回路径名
//	label1.setText(s);
//	
//}
