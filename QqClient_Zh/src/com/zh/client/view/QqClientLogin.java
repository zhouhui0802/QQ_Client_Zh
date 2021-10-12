package com.zh.client.view;

import java.awt.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;






import com.zh.client.tools.ManageClientConServerThread;
import com.zh.client.tools.ManageQqFriendList;
import com.zh.client.util.FrameUtil;
import com.zh.common.Message;
import com.zh.common.MessageType;
import com.zh.common.User;
import com.zh.model.QqClientUser;

public class QqClientLogin extends JFrame implements ActionListener,ChangeListener{

	//定义北部需要的组件
	JLabel jbl1;
	
	//定义中部需要的组件
	//中间有三个JPanel，有一个选项卡窗口管理
	JTabbedPane jtp;
	JPanel jp2,jp3,jp4;
	JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3,jp2_jbl4;
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1,jp2_jcb2;
	
	//定义南部需要的组件
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	
	//定义手机号码登录需要的组件
	JLabel phone,phonePassword,forgetPhone;
	JTextField phoneField;
	JPasswordField phonePasswordField;
	JButton phoneClear;
	
	//定义邮件登录需要的组件
	JLabel email,emailPassword,forgetEmail;
	JTextField emailField;
	JPasswordField emailPasswordField;
	JButton emailClear;
	
	public int selectIndex=0;
	
	public QqClientLogin()
	{
		//处理北部   上部QQ头像的位置
		jbl1=new JLabel(new ImageIcon("image/tou.gif"));
		
		//处理QQ号码登录的中部 
		jp2=new JPanel(new GridLayout(3,3));	
		jp2_jbl1=new JLabel("QQ号码",JLabel.CENTER);
		jp2_jbl2=new JLabel("QQ密码",JLabel.CENTER);
		
		jp2_jbl3=new JLabel("忘记密码",JLabel.CENTER);
		jp2_jbl3.setForeground(Color.blue);
		jp2_jbl4=new JLabel("申请密码保护",JLabel.CENTER);
		
		jp2_jb1=new JButton(new ImageIcon("image/clear.gif"));
		jp2_jtf=new JTextField();
		jp2_jpf=new JPasswordField();
		jp2_jcb1=new JCheckBox("隐身登录");
		jp2_jcb2=new JCheckBox("记住密码");
		
		//把控件加入到jp2中
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jbl3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jbl4);
		
		
		//对其余两个面板进行补充 用手机号码登录
		jp3=new JPanel(new GridLayout(2,3));
		phone=new JLabel("用户电话",JLabel.CENTER);
		phonePassword=new JLabel("电话密码",JLabel.CENTER);
		phoneField=new JTextField(3);
		phonePasswordField=new JPasswordField(3);
		phoneClear=new JButton(new ImageIcon("image/clear.gif"));
		forgetPhone=new JLabel("忘记电话",JLabel.CENTER);
		jp3.add(phone);
		jp3.add(phoneField);
		jp3.add(phoneClear);
		jp3.add(phonePassword);
		jp3.add(phonePasswordField);
		jp3.add(forgetPhone);
		
		
		//用电子邮件登录
		jp4=new JPanel(new GridLayout(2,3));
		email=new JLabel("用户邮件",JLabel.CENTER);
		emailPassword=new JLabel("邮件密码",JLabel.CENTER);
		emailField=new JTextField();
		emailPasswordField=new JPasswordField();
		emailClear=new JButton(new ImageIcon("image/clear.gif"));
		forgetEmail=new JLabel("忘记邮件",JLabel.CENTER);
		jp4.add(email);
		jp4.add(emailField);
		jp4.add(emailClear);
		jp4.add(emailPassword);
		jp4.add(emailPasswordField);
		jp4.add(forgetEmail);
		
		//创建选项卡窗口
		jtp=new JTabbedPane();
		
		jtp.addTab("QQ号码",jp2);
		jtp.addTab("手机号码",jp3);
		jtp.addTab("电子邮件",jp4);
		
		jtp.addChangeListener(this);
		
		//处理南部  三个登录按钮
		jp1=new JPanel(new FlowLayout());
		jp1_jb1=new JButton(new ImageIcon("image/denglu.gif"));
		//响应用户点击登录
		jp1_jb1.addActionListener(this);
		jp1_jb2=new JButton(new ImageIcon("image/quxiao.gif"));
		jp1_jb3=new JButton(new ImageIcon("image/xiangdao.gif"));
		
		//把三个按钮放入到jp1
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		

		
		this.add(jbl1,"North");
		this.add(jtp,"Center");
		//把jp1放到南部
		this.add(jp1,"South");
		
		
		
		
		this.setSize(350, 240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		FrameUtil.setFrameCenter(this);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QqClientLogin qcl=new QqClientLogin();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//如果用户点击登录 
		if(selectIndex==0)  //选中第一个面板
		{
			if(arg0.getSource()==jp1_jb1)
			{
				QqClientUser qqClientUser=new QqClientUser();
				
				User u=new User();
				u.setUserId(jp2_jtf.getText().trim());
				u.setPasswd(new String(jp2_jpf.getPassword()));
				u.setType("0");
				
				if(qqClientUser.checkUser(u))
				{
					try{
					
						QqFriendList qqList=new QqFriendList(u.getUserId());				
						ManageQqFriendList.addQqFriendList(u.getUserId(), qqList);
						
						
						System.out.println("第七步: 统计在线好友的请求,发送的结构体是用户编号和message_get_onLineFriend");						
						//发送一个要求返回在线好友的请求包
						ObjectOutputStream oos=new ObjectOutputStream(
								ManageClientConServerThread.getClientConServerThread(u.getUserId()).getS().getOutputStream());					
						//做一个Message
						Message m=new Message();
						m.setMesType(MessageType.message_get_onLineFriend);			
						//指明我要的这个qq好友的情况
						m.setSender(u.getUserId());
						oos.writeObject(m);
						
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					
					this.dispose();

				}
			}
			
			
		}else if(selectIndex==1)
		{
			if(arg0.getSource()==jp1_jb1)
			{
				QqClientUser qqClientUser=new QqClientUser();			
				User u=new User();
				String temp=phoneField.getText();
				String temp1=new String(phonePasswordField.getPassword());
				System.out.println(temp+" "+temp1);
				u.setPhone(temp);
				u.setPhonePasswd(temp1);
				u.setType("1");
				if(qqClientUser.checkUser(u))
				{
					QqFriendList qqList=new QqFriendList(u.getPhone());				
				}
				
				this.dispose();
			}
		}else if(selectIndex==2)
		{
			if(arg0.getSource()==jp1_jb1)
			{
				QqClientUser qqClientUser=new QqClientUser();			
				User u=new User();
				String temp=emailField.getText();
				String temp1=new String(emailPasswordField.getPassword());
				System.out.println(temp+" "+temp1);
				u.setEmail(temp);
				u.setEmailPasswd(temp1);
				u.setType("2");
				if(qqClientUser.checkUser(u))
				{
					QqFriendList qqList=new QqFriendList(u.getEmail());				
				}
				
				this.dispose();
			}
		}

	}
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		if(jtp.getSelectedIndex()==0)
		{
			selectIndex=0;
		}else if(jtp.getSelectedIndex()==1)
		{
			selectIndex=1;
		}else if(jtp.getSelectedIndex()==2)
		{
			selectIndex=2;
		}
	}

}
