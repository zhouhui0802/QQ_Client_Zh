package com.zh.client.test;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SwingExp extends JFrame implements ActionListener {
	JFileChooser chooser;
	FileFilter filter;

	public static void main(String arg[]) {
		new SwingExp();
	}

	public SwingExp() {
		JButton button;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setLocation(250, 150);
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());
		button = new JButton("Open");
		button.addActionListener(this);
		pane.add(button);
		button = new JButton("Save");
		button.addActionListener(this);
		pane.add(button);
		pack();
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);// ����ѡ��ģʽ���ȿ���ѡ���ļ��ֿ���ѡ���ļ���
		String extj[] = { "jpeg", "jpg" };
		filter = new FileNameExtensionFilter("JPEG Image", extj);
		chooser.setFileFilter(filter);// �����ļ���׺������
		String extt[] = { "tiff", "tif" };
		filter = new FileNameExtensionFilter("TIFF Image", extt);
		chooser.setFileFilter(filter);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		int retval;
		String selection = e.getActionCommand();
		if (selection.equals("Open")) {
			retval = chooser.showOpenDialog(this);// ��ʾ"�����ļ�"�Ի���
			if (retval == JFileChooser.APPROVE_OPTION) {// ���ɹ���
				File file = chooser.getSelectedFile();// �õ�ѡ����ļ���
				System.out.println("File to open " + file);
			}
		} else if (selection.equals("Save")) {
			File file = new File("mongrove.tiff");
			chooser.setSelectedFile(file);// ����Ĭ���ļ���
			retval = chooser.showSaveDialog(this);// ��ʾ�������ļ����Ի���
			if (retval == JFileChooser.APPROVE_OPTION) {
				file = chooser.getSelectedFile();
				System.out.println("File to save " + file);
			}
		}
	}
}