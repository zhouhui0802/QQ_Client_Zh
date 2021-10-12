package com.zh.client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectOutputStream;
import java.util.Date;

import javax.swing.*;

import com.zh.client.tools.ManageClientConServerThread;
import com.zh.client.tools.ManageMultiQqChat;
import com.zh.client.util.FrameUtil;
import com.zh.common.Message;
import com.zh.common.MessageType;

public class MultiQqChat  extends JFrame implements ActionListener{

    JTextArea jta;
    JTextField jtf;
    JButton jb;
    JPanel jp;
    JScrollPane jsp;
    private String ownerID;
    private String MultiChatID;
    public String getOwnerID(){
        return ownerID;
    }
    public String getMultiChatID(){
        return  MultiChatID;
    }
    public void setOwnerID(String ownerID){
        this.ownerID = ownerID;
    }
    public void setMultiChatID(String MultiChatID){
        this.MultiChatID = MultiChatID;
    }
    
    public MultiQqChat(String ownerID ,String MultiChatID)
    {
        this.ownerID = ownerID;
        this.MultiChatID = MultiChatID;

        jta = new JTextArea();
        jsp = new JScrollPane(jta);
        jtf = new JTextField(20);
        jb = new JButton("·¢ËÍ");
        jb.addActionListener(this);
        jp = new JPanel();

        jp.add(jtf);
        jp.add(jb);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                ManageMultiQqChat.removeMultiQqChat(ownerID+" "+MultiChatID);
            }
        });

        add(jsp,"Center");
        add(jp,"South");
        setTitle(MultiChatID+" ");
        setSize(400,300);
        //setLocationRelativeTo(null);
        setVisible(true);
        FrameUtil.setFrameCenter(this);
    }
    
    
    public void showMessages(Message m){
        String info = m.getSender()+"Ëµ£º"+m.getCon()+"\r\n";
        System.out.println("Multichat:"+info);
        jta.append(info);
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MultiQqChat mq=new MultiQqChat("1","2");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        if(e.getSource() == jb){
            Message m = new Message();
            m.setSender(this.ownerID);
            m.setMultiChat(this.MultiChatID);
            m.setCon(jtf.getText());
            m.setMesType(MessageType.message_multi);
            m.setSendTime(new Date().toString());
            String info = m.getSender()+"Ëµ£º"+m.getCon()+"\r\n";
            System.out.println(info);

            jta.append(info);
            jtf.setText("");

            try{
                ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(this.ownerID).getS().getOutputStream());
                oos.writeObject(m);
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
	}

}
