package com.swingMaven.delete;

import java.util.Random;

import org.hibernate.HibernateException;
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
			try {
				session.beginTransaction();
				int query = session.createQuery("DELETE FROM TAB1Database WHERE col1 = :index")
						.setParameter("index", index).executeUpdate();
				session.getTransaction().commit();
				index += 100;
				int number = 4000 + rand.nextInt(10000 - 4000 + 1);
				try {
					sleep(number);
				} catch (InterruptedException he) {
					he.printStackTrace();
				}
			} 
			catch (HibernateException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run(){
		startDeleteData();
	}
}
