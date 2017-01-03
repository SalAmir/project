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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainUI1 extends JFrame{

	private JPanel contentPane = null;
	private ThreadInsert threadInsert1 = null;
	private ThreadInsert threadInsert2 = null;
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
		tabUI1 = new TableUI1(contentPane, session);
		tabUI1.timerStop();
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Start_Insert1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isPressIns1)
				{
					threadInsert1 = new ThreadInsert(session);
					threadInsert1.setName("thread1");
					threadInsert1.start();
					System.out.println(threadInsert1.getName() + " start");
					tabUI1.timerStart();
					tabUI1.setVisible(true);
					isPressIns1 = true;
					isDisplay = true;
				}
				if (!tabUI1.isVisible()){
					tabUI1.timerStart();
					tabUI1.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(65, 11, 135, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Start_Insert2");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isPressIns2)
				{
					threadInsert2 = new ThreadInsert(session);
					threadInsert2.setName("thread2");
					threadInsert2.start();

					if (!tabUI1.isVisible()){
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
				threadInsert1.stopThread();
				tabUI1.setVisible(false);
				tabUI1.timerStop();
				isPressIns1 = false;
				System.out.println(threadInsert1.getName() + " stop");
			}
		});
		btnNewButton_3.setBounds(65, 113, 135, 23);
		panel.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Stop_Insert2");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				threadInsert2.stopThread();
				tabUI1.setVisible(false);
				tabUI1.timerStop();
				isPressIns1 = false;
				System.out.println(threadInsert2.getName() + " stop");
			}
		});
		btnNewButton_4.setBounds(65, 147, 135, 23);
		panel.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("Stop_Delete");
		btnNewButton_5.setBounds(65, 181, 135, 23);
		panel.add(btnNewButton_5);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				HibernateUtil.shutdown();
			}
		});
		
		setMinimumSize(new Dimension(270, 250));
		setLocationRelativeTo(null);
		setResizable(false);
	}
}
