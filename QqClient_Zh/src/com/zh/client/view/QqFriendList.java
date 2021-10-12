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

	//处理第一张卡片
	JPanel jphy1,jphy2,jphy3,jphy4; //四个个面板分别是好友，陌生人，黑名单以及群聊
	JButton jphy_jb1,jphy_jb2,jphy_jb3,jphy_jb4; //四个点击按钮
	JScrollPane jsp1;
	
	String owner;
	
	//好友的标签
	JLabel []jbls;
	Boolean[] jblsFlag;  //标记好友是否在线
	
	//群聊标签
	JLabel []jbls3;
	
	//标记群成员在线情况
	Boolean[] jbls2Flag;

	
	//处理第二张卡片
	JPanel jpmsr1,jpmsr2,jpmsr3,jpmsr4;
	JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3,jpmsr_jb4;
	JScrollPane jsp2;
	
	
	//处理第三章卡片
	JPanel jql1,jql2,jql3,jql4;
	JButton jql_jb1,jql_jb2,jql_jb3,jql_jb4;
	JScrollPane jsp3;
	

	
	//标记群联ID
	String MultichatID="qunliao";
	
	
	//把整个JFrame设置为CardLayout
	CardLayout cl;
	
	//处理在线列表
	public void updateFriend(Message m)
	{
		System.out.println("第十二步: 更新好友用户");
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
			System.out.println("onLineFriend的值是"+onLineFriend[i]);
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
		
		//处理第一张卡片(显示好友列表)
		jphy_jb1=new JButton("我的好友");
		jphy_jb2=new JButton("陌生人");
		jphy_jb2.addActionListener(this);
		jphy_jb3=new JButton("黑名单");
		jphy_jb4=new JButton("群聊天");
		jphy_jb4.addActionListener(this);
		
		jphy1=new JPanel(new BorderLayout());
		jphy4=new JPanel(new BorderLayout());
		
		//假定有50个好友
		jphy2=new JPanel(new GridLayout(50,1,4,4));
		//给jphy2,初始化50个好友
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
		//把三个按钮加入到jphy3中
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);	
		jphy3.add(jphy_jb4);	

	
		//对jphy1,初始化
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		
		
		
		//处理第二张卡片
		jpmsr_jb1=new JButton("我的好友");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2=new JButton("陌生人");
		jpmsr_jb3=new JButton("黑名单");
		jpmsr_jb4=new JButton("群聊天");
		jpmsr_jb4.addActionListener(this);
		
		jpmsr1=new JPanel(new BorderLayout());
		jpmsr4=new JPanel(new BorderLayout());
		
		//假定有20个陌生人
		jpmsr2=new JPanel(new GridLayout(20,1,4,4));
		//给jphy2,初始化20个陌生人
		JLabel []jbls2=new JLabel[20];	
		for(int i=0;i<jbls2.length;i++)
		{
			jbls2[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jbls2[i].setEnabled(false);
			jpmsr2.add(jbls2[i]);
		}
		jsp2=new JScrollPane(jpmsr2);
		
		jpmsr3=new JPanel(new GridLayout(2,1));
		//把两个按钮加入到jphy3中
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);	
		

		
		//对jphy1,初始化
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr4=new JPanel(new GridLayout(2,1));
		jpmsr4.add(jpmsr_jb3);
		jpmsr4.add(jpmsr_jb4);
		jpmsr1.add(jpmsr4,"South");
		
		
		//对第三张卡片进行处理
		jql_jb1=new JButton("我的好友");
		jql_jb1.addActionListener(this);
		jql_jb2=new JButton("陌生人");
		jql_jb2.addActionListener(this);
		jql_jb3=new JButton("黑名单");
		jql_jb4=new JButton("群聊天");

		
		jql1=new JPanel(new BorderLayout());
		jql2=new JPanel(new BorderLayout());
		//假定群聊中有20个人
		jql4=new JPanel(new GridLayout(20,1,4,4));
		//给jphy2,初始化20个陌生人
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
		//把两个按钮加入到jphy3中
		jql3.add(jql_jb1);
		jql3.add(jql_jb2);
		jql3.add(jql_jb3);
		jql3.add(jql_jb4);	
//		jpmsr3.add(jpmsr_jb4);	
		

		
		//对jphy1,初始化
		jql1.add(jql3,"North");
		jql1.add(jsp3,"Center");

		
		
		cl=new CardLayout();
		this.setLayout(cl);

		this.add(jphy1,"1");
		this.add(jpmsr1,"2");
		this.add(jql1,"3");
		
		//设置窗口关闭后给服务器发送离开的信息
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.out.println("进入到关闭函数中");
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
		
		//在窗口显示自己的编号
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
		if(arg0.getSource()==jphy_jb2||arg0.getSource()==jql_jb2)   //		jphy_jb2=new JButton("陌生人");  			jphy_jb2.addActionListener(this);
		{
			cl.show(this.getContentPane(), "2");
			
		}else if(arg0.getSource()==jpmsr_jb1||arg0.getSource()==jql_jb1)  		//处理第二张卡片    jpmsr_jb1=new JButton("我的好友");			
		{
			cl.show(this.getContentPane(), "1");
			
		}else if(arg0.getSource()==jphy_jb4||arg0.getSource()==jpmsr_jb4)
		{
			cl.show(this.getContentPane(), "3");
			//开始处理群聊消息
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
				JOptionPane.showMessageDialog(this, "不能和不在线的人聊天");
			}else if(!friendNo.equals(owner)&&jblsFlag[Integer.parseInt(friendNo)-1])
			{
				QqChat qqChat=new QqChat(this.owner,friendNo);
				ManageQqChat.addQqChat(this.owner+" "+friendNo, qqChat);
			}else
			{
				//如果是自己
				JOptionPane.showMessageDialog(this, "不能和自己聊天");
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
