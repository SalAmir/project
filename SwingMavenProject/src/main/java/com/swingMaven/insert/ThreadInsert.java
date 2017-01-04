package com.swingMaven.insert;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Random;

import org.hibernate.Session;

import com.swingMaven.model.TAB1Database;

public class ThreadInsert extends Thread{
	private Session session = null;
	private boolean isStop = false;
	private static int num = 0;
	public static Collection<TAB1Database> listData = new LinkedHashSet<TAB1Database>();
	
	public ThreadInsert(Session session) {
		this.session = session;
	}
	
	public void stopThread(){
		isStop = true;
	}
	
	private void startGeneratingData(){
		while (!isStop){
			synchronized (ThreadInsert.class) {
				System.out.println("Thread name - " + this.getName() + " num = " + num);
				Random rand = new Random();
				session.beginTransaction();
				TAB1Database data = new TAB1Database();
				data.setCOL1(num);
				data.setCOL2(Integer.toString(num));
				data.setCOL3(rand.nextInt(10000));
				data.setCOL4(Integer.toString(rand.nextInt(10000)));
				listData.add(data);
		    	session.save(data);
		    	session.getTransaction().commit();
		    	//session.clear();
				num += 100;
				try {
					int number = 2000 + rand.nextInt(10000 - 2000 + 1);
					sleep(number);
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void run() {
		startGeneratingData();
	}
}
