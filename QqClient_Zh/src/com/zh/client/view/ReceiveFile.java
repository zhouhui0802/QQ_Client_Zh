package com.zh.client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.filechooser.FileFilter;

import com.zh.client.util.FrameUtil;

public class ReceiveFile extends JFrame implements ActionListener{
	
	JLabel title;
	
	JLabel label1;
	JLabel label2;
	
	JButton jb1;
	JButton jb2;
	JButton jb3;
	JButton jb4;
	
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JLabel label6;
	
	Box box1,box2,box3,box4,box5;
	JProgressBar jpb;
	
	JFileChooser chooser;
	FileFilter filter;
	
	public File file;  //�ӷ���������
	File fileChoose;  //ѡ������洢·��

	String ownerId,friendId;
	
	public ReceiveFile(String ownerId,String friendId)
	{
		this.ownerId=ownerId;
		this.friendId=friendId;
		title=new JLabel(friendId+"����"+ownerId+"���ļ�",JLabel.CENTER);
		box1=Box.createHorizontalBox();
		box1.add(title);
		
		label1=new JLabel("�����ļ�·��");
		jb4=new JButton("...");
		jb4.addActionListener(this);
		jb2=new JButton("����");
		jb2.addActionListener(this);
		
		box2=Box.createHorizontalBox();
		box2.add(label1);
		box2.createHorizontalStrut(25);
		box2.add(jb4);
		box2.createHorizontalStrut(25);
		box2.add(jb2);
		

		
		jpb=new JProgressBar();
		box4=Box.createHorizontalBox();
		box4.createHorizontalStrut(25);
		box4.add(jpb);

		
		box5=Box.createVerticalBox();
		box5.createVerticalStrut(20);
		box5.add(box1);
		box5.add(box2);
		box5.add(box4);
		
		this.add(box5);
		this.setVisible(true);
		this.setSize(300, 150);
		FrameUtil.setFrameCenter(this);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReceiveFile rf=new ReceiveFile("1","2");
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb4)
		{
			System.out.println("1111");
			chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.showOpenDialog(this);//��ʾ�򿪵��ļ��Ի���
			fileChoose = chooser.getSelectedFile();// �õ�ѡ����ļ�����
			System.out.println("fileChoose="+fileChoose);
			label1.setText("����·��: "+fileChoose);
		}else if(arg0.getSource()==jb2)
		{

			try{
				double Percent=0;
				double sumRead=0;

				
				FileInputStream fis = new FileInputStream(file);	
				
				FileOutputStream fos = new FileOutputStream(fileChoose+"\\"+file.getName());
				
				
				byte[] buf = new byte[1024];
				//��¼ʵ�ʶ�ȡ�����ֽ�
				int n = 0;
				//ѭ����ȡ
				while((n = fis.read(buf)) != -1){
					double n1=n;
					sumRead+=n;
					
					
					double sumFile=file.length();
					System.out.println(sumFile);
					
					Percent=sumRead/sumFile;
					BigDecimal   b2   =   new   BigDecimal(Percent);  
					double   f2   =   b2.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
					System.out.println(f2);
					jpb.setValue((int)(f2*100));
					
					if(f2==1)
					{
						jb2.setText("�������");
					}
					//�����ָ���ļ�
					fos.write(buf);
				}
				
				fos.close();
				fis.close();
			}catch (Exception e){
				e.printStackTrace();
			}
			
			
			
			System.out.println("д��ɹ�");
		}
	}

}
