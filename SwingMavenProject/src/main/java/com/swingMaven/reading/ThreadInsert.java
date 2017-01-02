package com.swingMaven.reading;

import java.util.Random;

import org.hibernate.Session;

import com.swingMaven.model.TAB1Database;

public class ThreadInsert extends Thread{
	Session session = null;
	boolean isStop = false;
	static int num = 0;
	
	public ThreadInsert(Session session) {
		this.session = session;
	}
	
	public void stopThread(){
		isStop = true;
	}
	
	private void startGeneratingData(){
		while (!isStop){
			Random rand = new Random();
			int number = 2000 + rand.nextInt(10000 - 2000 + 1);
			try {
				sleep(number);
				session.beginTransaction();
				TAB1Database db = new TAB1Database();
				db.setCOL1(num);
				db.setCOL2(Integer.toString(num));
				db.setCOL3(rand.nextInt(10000));
				db.setCOL4(Integer.toString(rand.nextInt(10000)));
		    	session.save(db);
		    	session.getTransaction().commit();
		    	session.clear();
				num += 100;
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		startGeneratingData();
	}
}
