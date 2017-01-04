package com.swingMaven.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import com.swingMaven.insert.ThreadInsert;
import com.swingMaven.model.TAB1Database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.Iterator;

public class TableUI2 extends JFrame{
	private JPanel contentPane = null;
	private JTable tableUI2 = null;
	private DefaultTableModel tabModel = null;
	private Timer timer = null;
	
	public TableUI2(final JPanel mainPane){
		contentPane = new JPanel();
		setTitle("TableUI2");
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
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
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		tabModel = new DefaultTableModel(new Object[][]{}, new Object[]{"COL1","COL2","COL3","COL4"}){
			
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			};
		};
		
		tableUI2 = new JTable(tabModel);
		scrollPane.setViewportView(tableUI2);
		panel.setLayout(gl_panel);

		timer = new Timer(5000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dataTable();
			}
		});
		timer.start();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				timerStop();
			}
		});
		
		setMinimumSize(new Dimension(450, 300));
		setLocationRelativeTo(mainPane);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		repaint();
	}
	
	public void timerStop(){
		timer.stop();
	}
	
	public void timerStart(){
		timer.start();
	}
	
	private void dataTable(){
		Collection <TAB1Database> listData = ThreadInsert.listData;
		TAB1Database[] tabValues = new TAB1Database[listData.size()];
		Iterator<TAB1Database> it = listData.iterator();
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
		//listData.clear();
		listData = null;
		tabModel = new DefaultTableModel(valTAB1, new Object[]{"COL1","COL2","COL3","COL4"});
		tableUI2.setModel(tabModel);
		tableUI2.updateUI();
		tableUI2.repaint();
	}
}
