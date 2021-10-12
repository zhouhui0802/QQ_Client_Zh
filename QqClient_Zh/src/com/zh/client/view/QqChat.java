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
	File file ;//ʹ���ļ����ȡѡ����ѡ����ļ�

	
	//�������

	public QqChat(String ownerId,String friend)
	{
		this.ownerId=ownerId;
		this.friendId=friend;
		
		jta=new JTextArea();
		jsp=new JScrollPane(jta);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		jtf=new JTextField(15);
		jb=new JButton("����");
		
		label1=new JLabel("ѡ�����ļ���·��");
		jbFile=new JButton("ѡ���ļ�");
		
		jbSendFile=new JButton("�����ļ�");
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
		
		this.setTitle(ownerId+"���ں�"+friend+"����");
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
			String info=ownerId+"��"+friendId+"˵: "+jtf.getText();
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
			
			//���͸�������
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
			//����һ���µ�Message
//			Message m=new Message();
//			m.setSender(this.ownerId);
//			m.setGetter(this.friendId);
//			m.setMesType(MessageType.message_file);
//			m.setSendTime(new Date().toString());
//			m.setFile(file);		
//			String info=m.getSender()+"��"+m.getGetter()+"�����ļ�"+file.getName()+"\r\n";
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
			String info=m.getSender()+"��"+m.getGetter()+"˵: "+m.getCon();
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
			String info="�ļ�: "+m.getFile().getName()+"������ϣ��Ѿ�����\r\n";
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
//	jf.showOpenDialog(this);//��ʾ�򿪵��ļ��Ի���
//	file =  jf.getSelectedFile();//ʹ���ļ����ȡѡ����ѡ����ļ�
//	String s = file.getAbsolutePath();//����·����
//	label1.setText(s);
//	
//}
