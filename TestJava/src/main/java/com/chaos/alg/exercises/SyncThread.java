package com.chaos.alg.exercises;

public class SyncThread extends Thread {

	private volatile boolean ready = false;

	public void start() {
		ready = true;
	}

	public void run() {
		while (ready) {
			System.out.println("This is running!");
			try {
				Thread.sleep(1000 * 10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void cancle() {
		ready = false;
	}
}
