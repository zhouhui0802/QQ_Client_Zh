package com.zh.client.test;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class tishi extends JFrame{
	JOptionPane jp;
	//JOptionPane.showConfirmDialog(null, "�������?", "����",JOptionPane.YES_NO_OPTION);
	
	public tishi()
	{
		int n=jp.showConfirmDialog(null, "�������?", "�ļ�����",JOptionPane.YES_NO_OPTION);
		System.out.println(n);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		tishi ts=new tishi();
	}

}
