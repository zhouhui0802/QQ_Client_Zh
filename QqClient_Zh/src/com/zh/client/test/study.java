package com.zh.client.test;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class study {

	public static void main(String[] args) {

		JFrame jf = new JFrame();
		jf.setTitle("表格组件");
		jf.setSize(500, 400);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		Container c = jf.getContentPane();

		JTabbedPane tabbedPane = new JTabbedPane();// 创建一个默认的选项卡面板
		JLabel tabLabelA = new JLabel("选项卡A");
		JLabel tabLabelB = new JLabel("选项卡B");
		JLabel tabLabelC = new JLabel("选项卡C");
		// 将标签组件添加到选项卡中
		tabbedPane.addTab("选项卡A", tabLabelA);
		tabbedPane.addTab("选项卡B", tabLabelB);
		tabbedPane.addTab("选项卡C", tabLabelC);

		c.add(tabbedPane);
		jf.setVisible(true);

	}
}

