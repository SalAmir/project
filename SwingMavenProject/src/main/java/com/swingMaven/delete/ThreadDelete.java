package com.swingMaven.delete;

import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;

public class ThreadDelete extends Thread{
	private Session session = null;
	private static int index = 100;
	private boolean isStop = false;
	
	public ThreadDelete(Session session){
		this.session = session;
		this.setName("thread3");
	}
	
	public void stopThread(){
		isStop = true;
	}
	
	private void startDeleteData(){
		while (!isStop){
			Random rand = new Random();
			System.out.println("Thread name - " + this.getName() + " index = " + index);
			int query = session.createQuery("DELETE FROM TAB1Database WHERE col1 = :index")
					.setParameter("index", index).executeUpdate();
			index += 100;
			try {
				int number = 4000 + rand.nextInt(10000 - 4000 + 1);
				sleep(number);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run(){
		startDeleteData();
	}
}
