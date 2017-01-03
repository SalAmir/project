package com.swingMaven.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.swingMaven.hibernate.HibernateUtil;
import com.swingMaven.model.TAB1Database;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TableUI1 extends JFrame{
	private JPanel contentPane = null;
	private JTable tableUI1 = null;
	private DefaultTableModel tabModel = null;
	private Session currentSession = null;
	private Timer timer = null;
	
	public TableUI1(final JPanel mainPane, final Session session){
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				timerStop();
			}
		});
		setTitle("TableUI1");
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setMinimumSize(new Dimension(450, 300));
		setLocationRelativeTo(mainPane);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		currentSession = session;
		//currentSession = HibernateUtil.getSessionFactory().openSession();
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(13)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		tabModel = new DefaultTableModel(new Object[][]{}, new Object[]{"COL1","COL2","COL3","COL4"}){
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			};
		};
		
		tableUI1 = new JTable(tabModel);
		scrollPane.setViewportView(tableUI1);
		panel.setLayout(gl_panel);
		
		timer = new Timer(5000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				readFromTAB1();
			}
		});
		timer.start();
		
		repaint();
	}
	
	public void timerStop(){
		timer.stop();
	}
	
	public void timerStart(){
		timer.start();
	}
	
	private void readFromTAB1(){
		Query query = currentSession.createQuery("from TAB1Database");
		Collection collections = query.list();
		TAB1Database[] tabValues = new TAB1Database[collections.size()];
		Iterator<TAB1Database> it = collections.iterator();
		int countRow = 0;
		while (it.hasNext()){
			tabValues[countRow] = it.next();
			countRow++;
		}
		it = null;
		Object[][] valTAB1 = new Object[countRow][4];
		for (int i = 0; i < countRow; i++){
			valTAB1[i][0] = tabValues[i].getCOL1();
			valTAB1[i][1] = tabValues[i].getCOL2();
			valTAB1[i][2] = tabValues[i].getCOL3();
			valTAB1[i][3] = tabValues[i].getCOL4();
		}
		tabValues = null;
		countRow = 0;
		collections.clear();
		collections = null;
		tabModel = new DefaultTableModel(valTAB1, new Object[]{"COL1","COL2","COL3","COL4"});
		tableUI1.setModel(tabModel);
		tableUI1.updateUI();
		tableUI1.repaint();
	}
	
}
