package com.chaos.concurrency;

public class TestThread2 extends Thread {
	public void run() {
		int k = 0;
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("I'm running 2 " + k);
			k++;
		}
	}

	public static void main(String args[]) {
		Thread kk = new TestThread2();
		kk.start();
	}

}
