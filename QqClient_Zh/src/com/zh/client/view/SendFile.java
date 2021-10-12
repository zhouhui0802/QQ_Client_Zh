package com.zh.client.view;

import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import javax.swing.*;

import com.zh.client.tools.ManageClientConServerThread;
import com.zh.client.util.FrameUtil;
import com.zh.common.Message;
import com.zh.common.MessageType;

public class SendFile extends JFrame implements ActionListener{
	
	JLabel title;
	
	JLabel label1;
	JLabel label2;
	
	JButton jb1;
	JButton jb2;
	JButton jb3;
	
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JLabel label6;
	
	JProgressBar jpb;
	JFileChooser jf;	
	File file ;//ʹ���ļ����ȡѡ����ѡ����ļ�
	
	Box box1,box2,box3,box4,box5;
	
	String ownerId,friendId;
	
	public SendFile(String ownerId,String friendId)
	{
		this.ownerId=ownerId;
		this.friendId=friendId;
		
		this.setTitle(ownerId+"���ڸ�"+friendId+"�����ļ�");
		title=new JLabel("�ļ�����",JLabel.CENTER);
		box1=Box.createHorizontalBox();
		box1.add(title);
		
		label1=new JLabel("�����ļ�: ");
		label2=new JLabel("�ļ�·��");
		jb1=new JButton("...");
		jb1.addActionListener(this);
		jb2=new JButton("����");
		jb2.addActionListener(this);
		box2=Box.createHorizontalBox();

		box2.add(label1);
		box2.createHorizontalStrut(25);
		box2.add(jb1);
		box2.createHorizontalStrut(25);
		box2.add(label2);
		box2.createHorizontalStrut(25);
		box2.add(jb2);
		
		label3=new JLabel("��С");
		label4=new JLabel("0.0");
		label5=new JLabel("||�ѷ���");
		label6=new JLabel("0.0");
		box3=Box.createHorizontalBox();
		
		box3.add(label3);
		box3.createHorizontalStrut(25);
		box3.add(label4);
		box3.createHorizontalStrut(25);
		box3.add(label5);
		box3.createHorizontalStrut(25);
		box3.add(label6);
		
		jpb=new JProgressBar();
		jb3=new JButton("ֹͣ");
		box4=Box.createHorizontalBox();
		box4.createHorizontalStrut(25);
		box4.add(jpb);
		box4.add(jb3);
		
		box5=Box.createVerticalBox();
		box5.createVerticalStrut(20);
		box5.add(box1);
		box5.add(box2);
		box5.add(box3);
		box5.add(box4);
		
		this.add(box5);
		this.setVisible(true);
		this.setSize(400, 150);
		FrameUtil.setFrameCenter(this);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SendFile sf=new SendFile("1","1");
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb1)
		{
			jf = new JFileChooser();
			jf.showOpenDialog(this);//��ʾ�򿪵��ļ��Ի���
			file =  jf.getSelectedFile();//ʹ���ļ����ȡѡ����ѡ����ļ�
			String s = file.getAbsolutePath();//����·����
			double fileSize=((double)file.length())/1024/1024;
			
			BigDecimal   b   =   new   BigDecimal(fileSize);  
			double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  

			System.out.println(f1);
			String fileSzie=String.valueOf(f1)+"MB";
			label2.setText(s);
			label4.setText(fileSzie);
			
		}else if(arg0.getSource()==jb2)
		{
			System.out.println("������Ͱ�ť");
			try{			
				FileInputStream fis=new FileInputStream(file);
				//FileOutputStream fos=new FileOutputStream(file);
				byte[] buf=new byte[1024];			
				double Percent=0;
				System.out.println("file.length()="+file.length());
				//��¼ʵ�ʶ�ȡ�����ֽ�
				int n=0;	
				double sumRead=0;
				//ѭ����ȡ
				while((n=fis.read(buf))!=-1)
				{
					double n1=n;
					sumRead+=n;
					//System.out.println(sumRead);
					
					double hasRead=sumRead/1024/1024;
					BigDecimal   b1   =   new   BigDecimal(hasRead);  
					double   f1   =   b1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
					String fileSzie=String.valueOf(f1)+"MB";
					label6.setText(fileSzie);
					
					double sumFile=file.length();
					Percent=sumRead/sumFile;
					BigDecimal   b2   =   new   BigDecimal(Percent);  
					double   f2   =   b2.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
					System.out.println(f2);
					jpb.setValue((int)(f2*100));
					if(f2==1)
					{
						jb3.setText("�������");
					}

				}
				fis.close();
				
				//����һ���µ�Message
				Message m=new Message();
				m.setSender(ownerId);
				m.setGetter(friendId);
				m.setMesType(MessageType.message_file);
				m.setSendTime(new Date().toString());
				m.setFile(file);		
				String info=m.getSender()+"��"+m.getGetter()+"�����ļ�"+file.getName()+"\r\n";

			
	            try{
	                ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(this.ownerId).getS().getOutputStream());
	                oos.writeObject(m);
	                
	            } catch (Exception e){
	                e.printStackTrace();
	            }
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			System.out.println("�ļ��������");
			
		}else if(arg0.getSource()==jb3)
		{
			System.out.println("���ֹͣ��ť");
		}
	}

}
