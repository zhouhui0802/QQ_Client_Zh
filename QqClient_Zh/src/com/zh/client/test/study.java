package com.zh.client.test;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class study {

	public static void main(String[] args) {

		JFrame jf = new JFrame();
		jf.setTitle("������");
		jf.setSize(500, 400);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		Container c = jf.getContentPane();

		JTabbedPane tabbedPane = new JTabbedPane();// ����һ��Ĭ�ϵ�ѡ����
		JLabel tabLabelA = new JLabel("ѡ�A");
		JLabel tabLabelB = new JLabel("ѡ�B");
		JLabel tabLabelC = new JLabel("ѡ�C");
		// ����ǩ�����ӵ�ѡ���
		tabbedPane.addTab("ѡ�A", tabLabelA);
		tabbedPane.addTab("ѡ�B", tabLabelB);
		tabbedPane.addTab("ѡ�C", tabLabelC);

		c.add(tabbedPane);
		jf.setVisible(true);

	}
}

