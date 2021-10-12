package com.zh.client.view;

import java.awt.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;

import javax.swing.*;

import com.zh.client.tools.ManageClientConServerThread;
import com.zh.client.tools.ManageMultiQqChat;
import com.zh.client.tools.ManageQqChat;
import com.zh.client.util.FrameUtil;
import com.zh.common.Message;
import com.zh.common.MessageType;

public class QqFriendList extends JFrame implements ActionListener,MouseListener{

	//�����һ�ſ�Ƭ
	JPanel jphy1,jphy2,jphy3,jphy4; //�ĸ������ֱ��Ǻ��ѣ�İ���ˣ��������Լ�Ⱥ��
	JButton jphy_jb1,jphy_jb2,jphy_jb3,jphy_jb4; //�ĸ������ť
	JScrollPane jsp1;
	
	String owner;
	
	//���ѵı�ǩ
	JLabel []jbls;
	Boolean[] jblsFlag;  //��Ǻ����Ƿ�����
	
	//Ⱥ�ı�ǩ
	JLabel []jbls3;
	
	//���Ⱥ��Ա�������
	Boolean[] jbls2Flag;

	
	//����ڶ��ſ�Ƭ
	JPanel jpmsr1,jpmsr2,jpmsr3,jpmsr4;
	JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3,jpmsr_jb4;
	JScrollPane jsp2;
	
	
	//��������¿�Ƭ
	JPanel jql1,jql2,jql3,jql4;
	JButton jql_jb1,jql_jb2,jql_jb3,jql_jb4;
	JScrollPane jsp3;
	

	
	//���Ⱥ��ID
	String MultichatID="qunliao";
	
	
	//������JFrame����ΪCardLayout
	CardLayout cl;
	
	//���������б�
	public void updateFriend(Message m)
	{
		System.out.println("��ʮ����: ���º����û�");
		String onLineFriend[]=m.getCon().split(" ");
		
		for(int i=0;i<50;i++)
		{
			
			jbls[i].setEnabled(false);
			jblsFlag[i]=false;
		}
		
		for (int i = 0 ; i < 20 ; i++){
			jbls3[i].setEnabled(false);
			jbls2Flag[i] = false;
		}
		
		
		for(int i=0;i<onLineFriend.length;i++)
		{
			System.out.println("onLineFriend��ֵ��"+onLineFriend[i]);
		}

		for(int i=0;i<onLineFriend.length;i++)
		{
			jbls[Integer.parseInt(onLineFriend[i])-1].setEnabled(true);	
			jblsFlag[Integer.parseInt(onLineFriend[i]) - 1] = true;
			if (Integer.parseInt((onLineFriend[i])) < 20){
				jbls3[Integer.parseInt((onLineFriend[i]))-1].setEnabled(true);
				jbls2Flag[Integer.parseInt(onLineFriend[i])-1] = true;
			}
		}
	}
	
	
	
	public QqFriendList(String ownerId)
	{
		this.owner=ownerId;
		
		//�����һ�ſ�Ƭ(��ʾ�����б�)
		jphy_jb1=new JButton("�ҵĺ���");
		jphy_jb2=new JButton("İ����");
		jphy_jb2.addActionListener(this);
		jphy_jb3=new JButton("������");
		jphy_jb4=new JButton("Ⱥ����");
		jphy_jb4.addActionListener(this);
		
		jphy1=new JPanel(new BorderLayout());
		jphy4=new JPanel(new BorderLayout());
		
		//�ٶ���50������
		jphy2=new JPanel(new GridLayout(50,1,4,4));
		//��jphy2,��ʼ��50������
		jbls=new JLabel[50];	
		jblsFlag=new Boolean[50];
		for(int i=0;i<jbls.length;i++)
		{
			jbls[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jbls[i].setEnabled(false);
			jblsFlag[i]=false;
			
			if(jbls[i].getText().equals(ownerId))
			{
				jbls[i].setEnabled(true);
				jblsFlag[i]=true;
			}
			
			jbls[i].addMouseListener(this);
			jphy2.add(jbls[i]);
		}
		jsp1=new JScrollPane(jphy2);
		
		jphy3=new JPanel(new GridLayout(3,1));
		//��������ť���뵽jphy3��
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);	
		jphy3.add(jphy_jb4);	

	
		//��jphy1,��ʼ��
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		
		
		
		//����ڶ��ſ�Ƭ
		jpmsr_jb1=new JButton("�ҵĺ���");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2=new JButton("İ����");
		jpmsr_jb3=new JButton("������");
		jpmsr_jb4=new JButton("Ⱥ����");
		jpmsr_jb4.addActionListener(this);
		
		jpmsr1=new JPanel(new BorderLayout());
		jpmsr4=new JPanel(new BorderLayout());
		
		//�ٶ���20��İ����
		jpmsr2=new JPanel(new GridLayout(20,1,4,4));
		//��jphy2,��ʼ��20��İ����
		JLabel []jbls2=new JLabel[20];	
		for(int i=0;i<jbls2.length;i++)
		{
			jbls2[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jbls2[i].setEnabled(false);
			jpmsr2.add(jbls2[i]);
		}
		jsp2=new JScrollPane(jpmsr2);
		
		jpmsr3=new JPanel(new GridLayout(2,1));
		//��������ť���뵽jphy3��
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);	
		

		
		//��jphy1,��ʼ��
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr4=new JPanel(new GridLayout(2,1));
		jpmsr4.add(jpmsr_jb3);
		jpmsr4.add(jpmsr_jb4);
		jpmsr1.add(jpmsr4,"South");
		
		
		//�Ե����ſ�Ƭ���д���
		jql_jb1=new JButton("�ҵĺ���");
		jql_jb1.addActionListener(this);
		jql_jb2=new JButton("İ����");
		jql_jb2.addActionListener(this);
		jql_jb3=new JButton("������");
		jql_jb4=new JButton("Ⱥ����");

		
		jql1=new JPanel(new BorderLayout());
		jql2=new JPanel(new BorderLayout());
		//�ٶ�Ⱥ������20����
		jql4=new JPanel(new GridLayout(20,1,4,4));
		//��jphy2,��ʼ��20��İ����
		jbls3=new JLabel[20];	
		jbls2Flag=new Boolean[20];
		for(int i=0;i<jbls3.length;i++)
		{
			jbls3[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jbls3[i].setEnabled(false);
			jql4.add(jbls3[i]);
			jbls2Flag[i]=false;
		}
		jsp3=new JScrollPane(jql4);
		
		jql3=new JPanel(new GridLayout(4,1));
		//��������ť���뵽jphy3��
		jql3.add(jql_jb1);
		jql3.add(jql_jb2);
		jql3.add(jql_jb3);
		jql3.add(jql_jb4);	
//		jpmsr3.add(jpmsr_jb4);	
		

		
		//��jphy1,��ʼ��
		jql1.add(jql3,"North");
		jql1.add(jsp3,"Center");

		
		
		cl=new CardLayout();
		this.setLayout(cl);

		this.add(jphy1,"1");
		this.add(jpmsr1,"2");
		this.add(jql1,"3");
		
		//���ô��ڹرպ�������������뿪����Ϣ
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.out.println("���뵽�رպ�����");
				Message m =new Message();
				m.setMesType(MessageType.message_exit);
				m.setSender(owner);
				try{
					ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(owner).getS().getOutputStream());
					oos.writeObject(m);
				}catch (Exception e1){
					e1.printStackTrace();
				}
			}
		});
		
		//�ڴ�����ʾ�Լ��ı��
		this.setSize(250, 400);
		this.setVisible(true);
		this.setTitle(ownerId);
		FrameUtil.setFrameCenter(this);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QqFriendList qfl=new QqFriendList("1");
	}
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jphy_jb2||arg0.getSource()==jql_jb2)   //		jphy_jb2=new JButton("İ����");  			jphy_jb2.addActionListener(this);
		{
			cl.show(this.getContentPane(), "2");
			
		}else if(arg0.getSource()==jpmsr_jb1||arg0.getSource()==jql_jb1)  		//����ڶ��ſ�Ƭ    jpmsr_jb1=new JButton("�ҵĺ���");			
		{
			cl.show(this.getContentPane(), "1");
			
		}else if(arg0.getSource()==jphy_jb4||arg0.getSource()==jpmsr_jb4)
		{
			cl.show(this.getContentPane(), "3");
			//��ʼ����Ⱥ����Ϣ
			if(ManageMultiQqChat.getMultiQqChat(this.owner+" "+this.MultichatID)==null)
			{
				MultiQqChat mqc=new MultiQqChat(owner,MultichatID);
				ManageMultiQqChat.addMultiQqChat(this.owner+" "+this.MultichatID,mqc);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getClickCount()==2)
		{
			String friendNo=((JLabel)arg0.getSource()).getText();
			if(!jblsFlag[Integer.parseInt(friendNo)-1])
			{
				JOptionPane.showMessageDialog(this, "���ܺͲ����ߵ�������");
			}else if(!friendNo.equals(owner)&&jblsFlag[Integer.parseInt(friendNo)-1])
			{
				QqChat qqChat=new QqChat(this.owner,friendNo);
				ManageQqChat.addQqChat(this.owner+" "+friendNo, qqChat);
			}else
			{
				//������Լ�
				JOptionPane.showMessageDialog(this, "���ܺ��Լ�����");
			}

		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel j1=(JLabel)arg0.getSource();
		j1.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel j1=(JLabel)arg0.getSource();
		j1.setForeground(Color.black);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
