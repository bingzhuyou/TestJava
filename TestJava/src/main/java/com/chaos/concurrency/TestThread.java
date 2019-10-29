package com.chaos.concurrency;

public class TestThread {
	public static void main(String args[]) throws InterruptedException {
		Thread kk = new Thread() {
			public void run() {
				int k = 0;
				while (!currentThread().isInterrupted()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						currentThread().interrupt();
						System.out.println("I'm interrupted " + k);
						e.printStackTrace();
					}
					System.out.println("I'm running " + k);
					k++;
				}
			}
		};
		kk.start();
		Thread.sleep(12500);
		kk.interrupt();
	}

}
