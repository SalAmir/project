package com.swingMaven.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.classic.Session;

import com.swingMaven.hibernate.HibernateUtil;
import com.swingMaven.model.TAB1Database;
import com.swingMaven.reading.ThreadInsert;

public class MainUI1 extends JFrame{

	private JPanel contentPane = null;
	private ThreadInsert threadInsert = null;
	private TableUI1 tabUI1 = null;
	private int num = 0;
	private boolean isPressIns1 = false;
	private boolean isPressIns2 = false;
	private boolean isDisplay = false;
	private Session session = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI1 main = new MainUI1();
					main.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainUI1(){
		setTitle("\u041C\u0435\u043D\u044E   ver. 1.0.0");
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		session = HibernateUtil.getSessionFactory().openSession();
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Start_Insert1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isPressIns1)
				{
					threadInsert = new ThreadInsert(session);
					threadInsert.start();

					//if (tabUI1 == null){
						tabUI1 = new TableUI1(contentPane, session);
						tabUI1.setVisible(true);
					//}

					isPressIns1 = true;
					isDisplay = true;
					HibernateUtil.shutdown();
				}
				if (!tabUI1.isVisible())
					tabUI1.setVisible(true);
			}
		});
		btnNewButton.setBounds(65, 11, 135, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Start_Insert2");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isPressIns2)
				{
					threadInsert = new ThreadInsert(session);
					threadInsert.start();

					if ( tabUI1 == null){
						tabUI1 = new TableUI1(contentPane, session);
						tabUI1.setVisible(true);
					}
					
					isPressIns2 = true;
					isDisplay = true;
					HibernateUtil.shutdown();
				}
				if (!tabUI1.isVisible())
					tabUI1.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(65, 45, 135, 23);
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Start_Delete");
		btnNewButton_2.setBounds(65, 79, 135, 23);
		panel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Stop_Insert1");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				threadInsert.stopThread();
				//tabUI1.dispose();
				tabUI1.setVisible(false);
				tabUI1.timerStop();
				isPressIns1 = false;
				System.out.println("thread1 stop");
			}
		});
		btnNewButton_3.setBounds(65, 113, 135, 23);
		panel.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Stop_Insert2");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				threadInsert.stopThread();
				//tabUI1.dispose();
				tabUI1.setVisible(false);
				tabUI1.timerStop();
				isPressIns2 = false;
				System.out.println("thread2 stop");
			}
		});
		btnNewButton_4.setBounds(65, 147, 135, 23);
		panel.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("Stop_Delete");
		btnNewButton_5.setBounds(65, 181, 135, 23);
		panel.add(btnNewButton_5);

		setMinimumSize(new Dimension(270, 250));
		setLocationRelativeTo(null);
		setResizable(false);
	}
}
